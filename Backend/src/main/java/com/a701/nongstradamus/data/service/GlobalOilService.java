package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.dto.GlobalOilDto;
import org.springframework.scheduling.annotation.Scheduled;

public interface GlobalOilService {

    void createGlobalOil(GlobalOilDto dto);

    @Scheduled(cron="0 0 12 * * ?") // 매일 12시
    void callForecastApi();
}
