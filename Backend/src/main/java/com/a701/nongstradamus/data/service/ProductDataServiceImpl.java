package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.dto.PriceHistoryDto;
import com.a701.nongstradamus.data.entity.PriceHistoryRawEntity;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.mapper.PriceHistoryMapper;
import com.a701.nongstradamus.data.repository.PriceHistoryRawRepository;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.data.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    @Override
    public void updateProductData()  {
        List<ProductEntity> products = productRepository.findAll();
        for(ProductEntity product : products) {
            List<PriceHistoryRawEntity> priceHistoryRaws = priceHistoryRawRepository.findAllByNameLikeAndIsSavedFalse(
                "%" + product.getName() + "%");
            List<PriceHistoryDto> dtos = new ArrayList<PriceHistoryDto>();
            for (PriceHistoryRawEntity priceHistoryRaw : priceHistoryRaws) {
                if (product.getUnit() == null) {
                    product.setUnit(priceHistoryRaw.getUnit());
                    productRepository.save(product);
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
                priceHistoryRaw.setIsSaved(true);
            }
            priceHistoryRepository.saveAll(
                dtos.stream().map(PriceHistoryMapper.INSTANCE::fromDtoToEntity)
                    .collect(Collectors.toList()));
            priceHistoryRawRepository.saveAll(priceHistoryRaws);
        }
    }
}

