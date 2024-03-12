package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.OriginDto;
import com.a701.nongstradamus.data.dto.WholesaleMarketDto;
import com.a701.nongstradamus.data.entity.OriginEntity;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.entity.WholesaleMarketCodeEntity;
import com.a701.nongstradamus.data.entity.WholesaleMarketEntity;
import com.a701.nongstradamus.data.mapper.OriginMapper;
import com.a701.nongstradamus.data.mapper.WholesaleMarketMapper;
import com.a701.nongstradamus.data.repository.OriginRepository;
import com.a701.nongstradamus.data.repository.ProductRepository;
import com.a701.nongstradamus.data.repository.WholesaleMarketCodeRepository;
import com.a701.nongstradamus.data.repository.WholesaleMarketRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class WholesaleMarketDataServiceImpl implements WholesaleMarketDataService {

    private final WholesaleMarketRepository wholesaleMarketRepository;

    private final OriginRepository originRepository;

    private final ProductRepository productRepository;

    @Value("${key.wholesale-market-code.url}")
    private String codeUrl;

    @Value("${key.wholesale-market-code.key}")
    private String codeKey;

    @Value("${key.wholesale-market.url}")
    private String url;

    @Value("${key.wholesale-market.key}")
    private String key;

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateWholeMarketData() {
        List<ProductEntity> products = productRepository.findAll();
        for(ProductEntity product : products) {
            if (product.getWholesaleMarketCode() == null) { // 농산물 코드가 없으면
                // 농산물 코드를 가져온다.
                StringBuilder code = new StringBuilder();
                for (int p = 1; ; p++) {
                    StringBuilder codeUrlFull = new StringBuilder();
                    codeUrlFull.append(codeUrl)
                        .append("?serviceKey=").append(codeKey)
                        .append("&apiType=json")
                        .append("&pageNo=").append(p);
                    ResponseEntity<Map> res = OpenAPIManager.fetchJSON(codeUrlFull.toString());
                    List<Map> data = (List<Map>) (res.getBody().get("data"));
                    if (data == null) {
                        throw new EntityNotFoundException("트래픽 초과");
                    }
                    if (data.isEmpty()) {
                        throw new EntityNotFoundException("데이터를 찾을 수 없습니다.");
                    }
                    for (Map d : data) {
                        if (d.get("midName").equals(product.getName())) { // 품목 코드를 찾았다면
                            code.append(d.get("large")).append(d.get("mid"))
                                .append("small"); // 품목코드를 저장하고
                            break; // 스캔을 멈춘다.
                        }
                    }
                    if (code.length() == 6) { // 코드를 찾았다면
                        break; // 스캔을 멈춘다.
                    }
                }
                // 품목 코들르 셋팅한다.
                product.setWholesaleMarketCode(code.toString());
            }
            // 오늘부터 하루씩 과거로 가면서
            for (int day = 1; day <= 100 ; day++) {
                // 이미 저장된 데이터면 수행하지 않는다.
                LocalDate today = LocalDate.now().minusDays(day);
                List<WholesaleMarketEntity> wholesaleMarkets = wholesaleMarketRepository.findAllByProductAndDate(product, java.sql.Timestamp.valueOf(today.atStartOfDay()));
                if(!wholesaleMarkets.isEmpty()){
                    break;
                }
                // 도매가 데이터를 검색한다.
                LocalDate yesterday = today.minusDays(1);
                for(int p = 1; ; p++) {
                    StringBuilder urlFull = new StringBuilder();
                    urlFull.append(url)
                        .append("?serviceKey=").append(key)
                        .append("&apiType=json")
                        .append("&pageNo=").append(p)
                        .append("&saleDate=").append(today.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                        .append("&whsalCd=110001")
                        .append("&largeCd=").append(product.getWholesaleMarketCode().substring(0, 2))
                        .append("&midCd=").append(product.getWholesaleMarketCode().substring(2, 4));
                    System.out.println(urlFull.toString());
                    ResponseEntity<Map> res = OpenAPIManager.fetchJSON(urlFull.toString());
                    List<Map> data = (List<Map>) (res.getBody().get("data"));
                    if(data==null){
                        throw new EntityNotFoundException("트래픽 초과");
                    }
                    if(data.isEmpty()){
                        break; // 데이터가 없으면 탐색 종료
                    }
                    // 도매가 데이터를 파싱한다.
                    List<WholesaleMarketDto> dtos = new ArrayList<WholesaleMarketDto>();
                    for(Map d : data){
                        WholesaleMarketDto dto = new WholesaleMarketDto();
                        dto.setProduct(product);
                        String originName = (String) d.get("sanName");
                        List<OriginEntity> originList = originRepository.findAllByName(originName);
                        OriginEntity origin;
                        if(originList.isEmpty()){
                            OriginDto originDto = new OriginDto(originName);
                             origin = originRepository.save(OriginMapper.INSTANCE.fromDtoToEntity(originDto));
                        }else{
                            origin = originList.get(0);
                        }
                        dto.setOrigin(origin);
                        dto.setDate(java.sql.Timestamp.valueOf(today.atStartOfDay()));
                        dto.setPrice(Long.parseLong((String) d.get("avgAmt")));
                        switch((String) d.get("lvName")){
                            case "특": dto.setGrade(4); break;
                            case "상": dto.setGrade(3); break;
                            case "중": dto.setGrade(2); break;
                            case "하": dto.setGrade(1); break;
                        }
                        dtos.add(dto);
                    }
                    // 도매가 데이터를 저장한다.
                    wholesaleMarketRepository.saveAll(dtos.stream().map(WholesaleMarketMapper.INSTANCE::fromDtoToEntity).collect(Collectors.toList()));
                }
            }
        }
    }

}
