package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.dto.DomesticOilDto;
import org.springframework.scheduling.annotation.Scheduled;

public interface DomesticOilService {

    void createDomesticOil(DomesticOilDto dto);

    void callDomesticOilSheduler();
}
