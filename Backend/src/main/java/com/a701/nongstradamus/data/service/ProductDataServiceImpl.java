package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.PriceHistoryDto;
import com.a701.nongstradamus.data.entity.PriceHistoryEntity;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.mapper.PriceHistoryMapper;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.data.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class ProductDataServiceImpl implements ProductDataService{

    private final ProductRepository productRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    @Value("${key.product.url}")
    private String urlBase;

    @Value("${key.product.id}")
    private String id;

    @Value("${key.product.password}")
    private String password;

    @Override
    @Scheduled(cron = "0 * * * * *")
    public void updateProductData() throws IOException, ParserConfigurationException, SAXException {
        // 검색할 품목을 가져옴
        List<ProductEntity> productList = productRepository.findAll();
        if(productList.isEmpty()){
            throw new EntityNotFoundException("농산물 데이터가 없습니다.");
        }
        for(ProductEntity product : productList) {
            // oopen api 검색 url 생성
            StringBuilder urlFull = new StringBuilder();
            urlFull.append(urlBase).append("?id=").append(id).append("&passwd=").append(password); // url, id, password
            urlFull.append("&dataid=data52")
                .append("&pagesize=100").append("&pageidx=1")
                .append("&portal.templet=false");
            LocalDate today = LocalDate.now().minusDays(1);
            LocalDate yesterday = today.minusDays(1);
            LocalDate lastYear = today.minusYears(1);
            urlFull.append("&p_ymd=").append(today.format(DateTimeFormatter.ofPattern("yyyyMMdd"))) // 어제 날짜
                .append("&p_jymd=").append(yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"))) // 그제 날짜
                .append("&d_cd=2") // 청과
                .append("&p_jjymd=").append(lastYear.format(DateTimeFormatter.ofPattern("yyyyMMdd"))) // 지난해 날짜
                .append("&p_pos_gubun=1") // 가락시장
                .append("&pum_nm=").append(product.getName()); // 품목명
            // api 검색
            ResponseEntity<String> res = OpenAPIManager.fetch(urlFull.toString());
            // 데이터 파싱
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(res.getBody())));
            NodeList lists = document.getElementsByTagName("list");
            NodeList price = document.getElementsByTagName("PUM_CD");
            NodeList ratio = document.getElementsByTagName("A_B");
            NodeList grade = document.getElementsByTagName("G_NAME_A");
            NodeList unit = document.getElementsByTagName("U_NAME");
            // 단위가 없다면 단위 먼저 저장
            if(product.getUnit() == null || product.getUnit().isEmpty()) {
                product.setUnit(unit.item(0).getFirstChild().getNodeValue());
                productRepository.save(product);
            }
            List<PriceHistoryDto> dtos = new ArrayList<PriceHistoryDto>();
            for(int i=0; i<lists.getLength();i++){
                PriceHistoryDto dto = new PriceHistoryDto();
                dto.setProduct(product);
                dto.setDate(java.sql.Timestamp.valueOf(today.atStartOfDay()));
                dto.setPrice(Long.parseLong(price.item(i).getFirstChild().getNodeValue()));
                dto.setRatio(Double.parseDouble(ratio.item(i).getFirstChild().getNodeValue()));
                switch(grade.item(i).getFirstChild().getNodeValue()) {
                    case "특":
                        dto.setGrade(4);
                        break;
                    case "상":
                        dto.setGrade(3);
                        break;
                    case "중":
                        dto.setGrade(2);
                        break;
                    case "하":
                        dto.setGrade(1);
                        break;
                }
                dtos.add(dto);
            }
            // 데이터 저장
            priceHistoryRepository.saveAll(dtos.stream().map(PriceHistoryMapper.INSTANCE::fromDtoToEntity).collect(Collectors.toList()));
        }
    }

}
