package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.dto.PriceHistoryDto;
import com.a701.nongstradamus.data.entity.PriceHistoryEntity;
import com.a701.nongstradamus.data.entity.PriceHistoryRawEntity;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.mapper.PriceHistoryMapper;
import com.a701.nongstradamus.data.repository.PriceHistoryRawRepository;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.data.repository.ProductRepository;
import com.a701.nongstradamus.main.entity.PricePredictEntity;
import com.a701.nongstradamus.main.repository.PricePredictRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.parser.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class ProductDataServiceImpl implements ProductDataService{

    private final ProductRepository productRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    private final PriceHistoryRawRepository priceHistoryRawRepository;

    private final PricePredictRepository pricePredictRepository;

    @Scheduled(cron = "0 0 18 * * *")
    @Override
//    @Scheduled(cron = "0 46 15 * * *")
    public void updateProductData()  {
        System.out.println("소매 가격 데이터 수집 시작");
        List<ProductEntity> products = productRepository.findAll();
        for(ProductEntity product : products) {
            List<PriceHistoryRawEntity> priceHistoryRaws = priceHistoryRawRepository.findAllByNameLikeAndIsSavedFalse(
                "%" + product.getName() + "%");
            List<PriceHistoryDto> dtos = new ArrayList<>();
            for (PriceHistoryRawEntity priceHistoryRaw : priceHistoryRaws) {
                if (product.getUnit() == null) {
                    product.setUnit(priceHistoryRaw.getUnit());
                    productRepository.save(product);
                }else{
                   if( !priceHistoryRaw.getUnit().equals(product.getUnit())){
                       continue;
                   }
                }
                PriceHistoryDto dto = new PriceHistoryDto();
                dto.setProduct(product);
                dto.setRatio(priceHistoryRaw.getRatio());
                dto.setDate(priceHistoryRaw.getDate());
                dto.setPrice(priceHistoryRaw.getPrice());
                switch (priceHistoryRaw.getGrade()) {
                    case "특상품":
                        dto.setGrade(4);
                        break;
                    case "상품":
                        dto.setGrade(3);
                        break;
                    case "중품":
                        dto.setGrade(2);
                        break;
                    case "하품":
                        dto.setGrade(1);
                        break;
                    default:
                        dto.setGrade(0);
                }
                dtos.add(dto);
                System.out.println(dto);
                priceHistoryRaw.setIsSaved(true);
            }
            priceHistoryRepository.saveAll(
                dtos.stream().map(PriceHistoryMapper.INSTANCE::fromDtoToEntity)
                    .collect(Collectors.toList()));
            priceHistoryRawRepository.saveAll(priceHistoryRaws);
        }
        System.out.println("데이터 수집 끝");
        System.out.println("데이터 정제 시작");
        for (ProductEntity product : products){
            System.out.println(product);
            for(int day = 30; day >= 0; day--){
                LocalDate today = LocalDate.now().minusDays(day);
                for(int grade = 1; grade <= 4; grade++) {
                    List<PriceHistoryEntity> logs = priceHistoryRepository.findAllByProductAndDateAndGrade(
                        product, java.sql.Timestamp.valueOf(today.atStartOfDay()), grade);
                    if (logs.size() == 0) {
                        List<PricePredictEntity> futureLogs = pricePredictRepository.findAllByProductAndDateAndGrade(
                            product, today, grade);
                        if (!futureLogs.isEmpty()) {
                            PriceHistoryEntity entity = new PriceHistoryEntity();
                            entity.setDate(java.sql.Timestamp.valueOf(today.atStartOfDay()));
                            entity.setProduct(product);
                            entity.setRatio(0.0);
                            entity.setGrade(grade);
                            entity.setPrice(futureLogs.get(0).getPrice());
                            priceHistoryRepository.save(entity);
                            continue;
                        }
                        List<PriceHistoryEntity> previousLogs = priceHistoryRepository.findAllByProductAndDateAndGrade(
                            product, java.sql.Timestamp.valueOf(today.minusDays(1).atStartOfDay()),
                            grade);
                        if (previousLogs.isEmpty()) {
                            previousLogs = priceHistoryRepository.findAllByProductAndDateAndGrade(
                                product, java.sql.Timestamp.valueOf(today.atStartOfDay()),
                                grade - 1);
                            if (previousLogs.isEmpty()) {
                                previousLogs = priceHistoryRepository.findAllByProductAndDateAndGrade(
                                    product, java.sql.Timestamp.valueOf(today.atStartOfDay()),
                                    grade + 1);
                                if (previousLogs.isEmpty()) {
                                    previousLogs = priceHistoryRepository.findAllByProductAndDateAndGrade(
                                        product, java.sql.Timestamp.valueOf(today.atStartOfDay()),
                                        grade + 2);
                                    if(previousLogs.isEmpty()){
                                        previousLogs = priceHistoryRepository.findAllByProductAndDateAndGrade( product, java.sql.Timestamp.valueOf(today.atStartOfDay()), grade - 2);
                                    }
                                }
                            }
                        }
                        if (!previousLogs.isEmpty()) {
                            PriceHistoryEntity entity = new PriceHistoryEntity();
                            entity.setDate(java.sql.Timestamp.valueOf(today.atStartOfDay()));
                            entity.setProduct(product);
                            entity.setRatio(0.0);
                            entity.setGrade(grade);
                            entity.setPrice(previousLogs.get(0).getPrice());
                            priceHistoryRepository.save(entity);
                        }
                    }else if (logs.size() >= 2) {
                        PriceHistoryEntity ett = new PriceHistoryEntity();
                        ett.setGrade(grade);
                        ett.setProduct(product);
                        ett.setDate(java.sql.Timestamp.valueOf(today.atStartOfDay()));
                        ett.setPrice(
                            (long) logs.stream().mapToDouble(entity1 -> entity1.getPrice()).average()
                                .getAsDouble());
                        ett.setRatio(
                            logs.stream().mapToDouble(entity1 -> entity1.getRatio()).average()
                                .getAsDouble());
                        priceHistoryRepository.deleteAll(logs);
                        priceHistoryRepository.save(ett);
                    }
                }
            }
        }
        System.out.println("데이터 정제 끝");
    }
}

