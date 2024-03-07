package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.repository.ProductRepository;
import com.a701.nongstradamus.data.repository.WholesaleMarketRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class WholesaleMarketDataServiceImpl implements WholesaleMarketDataService {

    private final WholesaleMarketRepository wholesaleMarketRepository;

    private final ProductRepository productRepository;

    @Override
    @Scheduled(cron = "* * * * * *")
    public void updateWholeMarketData() {
        List<ProductEntity> products = productRepository.findAll();
        for(ProductEntity product : products){
            if(product.getWholeMarketCode()==null){
                // 농산물 코드를 가져온다.
            }
            
        }
    }

}
