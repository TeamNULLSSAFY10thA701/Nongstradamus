package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.repository.ProductRepository;
import com.a701.nongstradamus.data.repository.WholesaleMarketRepository;
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
    @Scheduled(cron = "0 0 0 * * *")
    public void updateWholeMarketData() {

    }

}
