package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.RecipeDto;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.entity.WeatherEntity;
import com.a701.nongstradamus.data.mapper.RecipeMapper;
import com.a701.nongstradamus.data.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.xml.parsers.ParserConfigurationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class WeatherDataServiceImpl implements WeatherDataService {

    private final WeatherRepository weatherRepository;

    @Value("${key.weather.url}")
    private String urlBase;

    @Value("${key.weather.api}")
    private String apiKey;

    //테스트용
    //@Override
    //@Scheduled(fixedDelay = 10000000)

    // 매일 0시에 실행
//    @Override
//    @Scheduled(cron = "0 0 0 * * ?")

    //테스트용
    @Override
    @Scheduled(fixedDelay = 10000000)
    public void updateWeatherData() {

        LocalDate today = LocalDate.now();

        //a일전 데이터 검색 후 없으면 등록 -> a-1일전 데이터 검색 -> 최대 100일전까지 등록
        for(int minusDay = 0; minusDay <= 100; minusDay++){
            LocalDate date  = today.minusDays(minusDay);

            //검색
            List<WeatherEntity> weatherList = weatherRepository.findByDate(date);

            if(weatherList.isEmpty()){
                //등록
            }
        }

    }

}
