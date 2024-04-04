package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.dto.GlobalOilDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.scheduling.annotation.Scheduled;

public interface GlobalOilService {

    void createGlobalOil(GlobalOilDto dto);

    void callGlobalOilScheduler() throws JsonProcessingException;
}
