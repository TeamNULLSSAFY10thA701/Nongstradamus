package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.OriginDto;
import com.a701.nongstradamus.data.dto.WholesaleMarketDto;
import com.a701.nongstradamus.data.entity.OriginEntity;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.entity.WholesaleMarketEntity;
import com.a701.nongstradamus.data.mapper.OriginMapper;
import com.a701.nongstradamus.data.mapper.WholesaleMarketMapper;
import com.a701.nongstradamus.data.repository.OriginRepository;
import com.a701.nongstradamus.data.repository.ProductRepository;
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
import org.springframework.transaction.annotation.Transactional;

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
//    @Scheduled(fixedRate = 1000000000)
    public void updateWholeMarketData() {
        List<ProductEntity> products = productRepository.findAll();
//        List<Map> data1 = new ArrayList<Map>();
//        for (int p = 1; ; p++) {
//            StringBuilder codeUrlFull = new StringBuilder();
//            codeUrlFull.append(codeUrl)
//                .append("?serviceKey=").append(codeKey)
//                .append("&apiType=json")
//                .append("&pageNo=").append(p);
//            ResponseEntity<Map> res = OpenAPIManager.fetchJSON(codeUrlFull.toString());
//            List<Map> dts = (List<Map>) (res.getBody().get("data"));
//            if (dts == null) {
//                throw new EntityNotFoundException("트래픽 초과");
//            }
//            if (dts.isEmpty()) {
//                break;
//            }
//            for(Map dt : dts){
//                data1.add(dt);
//            }
//        }
//        for(ProductEntity product : products){
//            if(product.getWholesaleMarketCode()==null){
//                for(Map dt : data1){
//                    if(dt.get("midName").equals(product.getName())){
//                        StringBuilder code = new StringBuilder().append(dt.get("large")).append(dt.get("mid")).append(dt.get("small"));
//                        product.setWholesaleMarketCode(code.toString());
//                        productRepository.save(product);
//                        break;
//                    }
//                }
//            }
//        }
        // 품목 코들르 셋팅한다.
        for(ProductEntity product : products) {
            // 오늘부터 하루씩 과거로 가면서
            for (int day = 1; day <= 10 ; day++) {
                // 이미 저장된 데이터면 수행하지 않는다.
                LocalDate today = LocalDate.now().minusDays(day);
                List<WholesaleMarketEntity> wholesaleMarkets = wholesaleMarketRepository.findAllByProductAndDate(product, java.sql.Timestamp.valueOf(today.atStartOfDay()));
                if(!wholesaleMarkets.isEmpty()){
                    continue;
                }
                System.out.println(today);
                // 도매가 데이터를 검색한다.
                if(product.getWholesaleMarketCode() == null){
                    continue;
                }
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
                    ResponseEntity<Map> res = OpenAPIManager.fetchJSON(urlFull.toString());
                    List<Map> data = (List<Map>) (res.getBody().get("data"));
                    if(data==null){
                        System.out.println(res.getBody());
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
                        dto.setPrice(Long.parseLong( d.get("totAmt").toString()));
                        switch((String) d.get("lvName")){
                            case "특": dto.setGrade(4); break;
                            case "상": dto.setGrade(3); break;
                            case "중": dto.setGrade(2); break;
                            case "하": dto.setGrade(1); break;
                            default: dto.setGrade(0);
                        }
                        dtos.add(dto);
                    }
                    // 도매가 데이터를 저장한다.
                    if(!dtos.isEmpty())
                        wholesaleMarketRepository.saveAll(dtos.stream().map(WholesaleMarketMapper.INSTANCE::fromDtoToEntity).collect(Collectors.toList()));
                }
            }
        }
        System.out.println("도매 시장 데이터 수집 완료");
        System.out.println("도매 시장 데이터 정제 시작");
        for(ProductEntity product : products){
            for(int day = 1; day <= 10; day++){
                LocalDate today = LocalDate.now().minusDays(day);
                for(int grade = 1; grade <=4; grade++){
                    List<OriginEntity> origins = wholesaleMarketRepository.findOriginIdByProductAndDateAndGrade(
                        product, java.sql.Timestamp.valueOf(today.atStartOfDay()), grade
                    );
                    for(OriginEntity origin : origins){
                        List<WholesaleMarketEntity> logs = wholesaleMarketRepository.findAllByProductAndDateAndGradeAndOrigin(
                            product, java.sql.Timestamp.valueOf(today.atStartOfDay()), grade, origin
                        );
                        if(logs.isEmpty()){
                            logs = wholesaleMarketRepository.findAllByProductAndDateAndGradeAndOrigin(
                                product, java.sql.Timestamp.valueOf(today.minusDays(1).atStartOfDay()), grade, origin
                            );
                            if(logs.isEmpty())
                                logs = wholesaleMarketRepository.findAllByProductAndDateAndGradeAndOrigin(
                                    product, java.sql.Timestamp.valueOf(today.atStartOfDay()), grade - 1, origin
                                );
                            if(logs.isEmpty())
                                logs = wholesaleMarketRepository.findAllByProductAndDateAndGradeAndOrigin(
                                    product, java.sql.Timestamp.valueOf(today.atStartOfDay()), grade + 1, origin
                                );
                            if(logs.isEmpty())
                                logs = wholesaleMarketRepository.findAllByProductAndDateAndGradeAndOrigin(
                                    product, java.sql.Timestamp.valueOf(today.atStartOfDay()), grade + 2, origin
                                );
                            if(logs.isEmpty())
                                logs = wholesaleMarketRepository.findAllByProductAndDateAndGradeAndOrigin(
                                    product, java.sql.Timestamp.valueOf(today.atStartOfDay()), grade + 3, origin
                                );
                            if(!logs.isEmpty()) {
                                WholesaleMarketEntity e = new WholesaleMarketEntity();
                                e.setProduct(product);
                                e.setGrade(grade);
                                e.setOrigin(origin);
                                e.setDate(java.sql.Timestamp.valueOf(today.atStartOfDay()));
                                e.setPrice((long) logs.stream()
                                    .mapToDouble(entity -> (double) entity.getPrice()).average()
                                    .getAsDouble());
                                wholesaleMarketRepository.save(e);
                            }
                        }else if(logs.size() >= 2) {
                            WholesaleMarketEntity e = new WholesaleMarketEntity();
                            e.setProduct(product);
                            e.setGrade(grade);
                            e.setOrigin(origin);
                            e.setDate(java.sql.Timestamp.valueOf(today.atStartOfDay()));
                            e.setPrice((long) logs.stream()
                                .mapToDouble(entity -> (double) entity.getPrice()).average()
                                .getAsDouble());
                            wholesaleMarketRepository.deleteAll(logs);
                            wholesaleMarketRepository.save(e);
                        }
                    }
                }
            }
        }
        System.out.println("도매 시장 데이터 정제 끝");
    }

}
