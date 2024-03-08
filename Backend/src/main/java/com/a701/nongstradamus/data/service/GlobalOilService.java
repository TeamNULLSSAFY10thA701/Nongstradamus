package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.dto.GlobalOilDto;
import org.springframework.scheduling.annotation.Scheduled;

public interface GlobalOilService {

    void createGlobalOil(GlobalOilDto dto);

    void callGlobalOilScheduler();
}
