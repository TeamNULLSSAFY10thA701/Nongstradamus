package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDataService {
    ProductRepository productRepository;

    PriceHistoryRepository priceHistoryRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateProductData(){

    }

}
