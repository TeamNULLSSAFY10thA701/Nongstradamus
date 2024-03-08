package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.TradeDto;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.entity.TradeEntity;
import com.a701.nongstradamus.data.mapper.TradeMapper;
import com.a701.nongstradamus.data.repository.ProductRepository;
import com.a701.nongstradamus.data.repository.TradeRepository;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class TradeDataServiceImpl implements TradeDataService {

    private final TradeRepository tradeRepository;

    private final ProductRepository productRepository;

    @Value("${key.trade.url}")
    private String url;

    @Value("${key.trade.key}")
    private String key;

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateImportData() throws ParserConfigurationException, IOException, SAXException {
        // 풀목 리스트 조회
        List<ProductEntity> products = productRepository.findAll();
        // 각 품목에 대하여
        for(ProductEntity product : products){
            // 한달 씩 뒤로 가면서
            for(int month = 1; month <= 12; month++){
                YearMonth thisMonth = YearMonth.now().minusMonths(month);
                // 품목 코드가 null 일경우
                if(product.getTrade().getCode() == null){
                    List<TradeDto> dtos = new ArrayList<TradeDto>();
                    for(int d=1; thisMonth.isValidDay(d); d++){
                        LocalDate today = thisMonth.atDay(d);
                        TradeDto dto = new TradeDto();
                        dto.setDate(java.sql.Timestamp.valueOf(today.atStartOfDay()));
                        dto.setProduct(product);
                        dto.setAmount(0);
                        dto.setBalance(0);
                        dto.setVolume(0);
                    }
                    tradeRepository.saveAll(dtos.stream().map(TradeMapper.INSTANCE::fromDtoToEntity).collect(Collectors.toList()));
                    continue;
                }
                StringBuilder urlFull = new StringBuilder();
                urlFull.append(url)
                    .append("?serviceKey=").append(key)
                    .append("&strtYymm=").append(thisMonth.format(DateTimeFormatter.ofPattern("yyyyMM")))
                    .append("&endYymm=").append(thisMonth.format(DateTimeFormatter.ofPattern("yyyyMM")))
                    .append("&hsSgn=").append(product.getTrade().getCode());
                // api 호출
                ResponseEntity<String> res = OpenAPIManager.fetchXML(urlFull.toString());
                // 데이터 파싱
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(res.getBody())));
                NodeList items = document.getElementsByTagName("item");
                if(items.getLength()==0){
                    continue;
                }
                NodeList item = items.item(0).getChildNodes();
                Integer balance = Integer.parseInt((String) item.item(0).getNodeValue());
                Integer amount = Integer.parseInt((String) item.item(4).getNodeValue()) - Integer.parseInt((String) item.item(1).getNodeValue());
                Integer volume = Integer.parseInt((String) item.item(5).getNodeValue()) - Integer.parseInt((String) item.item(2).getNodeValue());
                List<TradeDto> dtos = new ArrayList<TradeDto>();
                for(int d = 1; thisMonth.isValidDay(d); d++){
                    TradeDto dto = new TradeDto();
                    LocalDate today = thisMonth.atDay(d);
                    dto.setProduct(product);
                    dto.setDate(java.sql.Timestamp.valueOf(today.atStartOfDay()));
                    dto.setBalance(balance);
                    dto.setVolume(volume);
                    dto.setAmount(amount);
                    dtos.add(dto);
                }
                // 데이터 저장
                tradeRepository.saveAll(dtos.stream().map(TradeMapper.INSTANCE::fromDtoToEntity).collect(Collectors.toList()));
            }
        }
    }
}
