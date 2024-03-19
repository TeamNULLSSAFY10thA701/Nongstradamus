package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.DomesticOilDto;
import com.a701.nongstradamus.data.entity.DomesticOilEntity;
import com.a701.nongstradamus.data.mapper.DomesticOilMapper;
import com.a701.nongstradamus.data.repository.DomesticOilRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class DomesticOilServiceImpl implements DomesticOilService{

    @Value("${key.domesticOil.url}")
    private String callBackUrl;
    @Value("${key.domesticOil.servicekey}")
    private String serviceKey;

    private final DomesticOilRepository domesticOilRepository;

    @Override
    public void createDomesticOil(DomesticOilDto dto) {

        DomesticOilEntity entity = DomesticOilMapper.INSTANCE.fromDtoToEntity(dto);

        domesticOilRepository.save(entity);

    }

    @Override
    @Scheduled(cron="0 0 0 * * *") // 매일 자정
    @Transactional
    public void callDomesticOilSheduler(){
        LocalDate today = LocalDate.now();
        String dataType = "json";

        String urlStr = callBackUrl +
                "?out=" + dataType +
                "&code=" + serviceKey;
        System.out.println(urlStr);
        // Parse JSON string using org.json.JSONObject
        JSONObject jsonObject = OpenAPIManager.fetchData(urlStr);
        JSONArray resultObjects = jsonObject.getJSONObject("RESULT").getJSONArray("OIL");

        DomesticOilDto domesticOilDto = new DomesticOilDto(0,
                resultObjects.getJSONObject(0).getDouble("PRICE"),
                resultObjects.getJSONObject(1).getDouble("PRICE"),
                resultObjects.getJSONObject(2).getDouble("PRICE"),
                today
        );
        createDomesticOil(domesticOilDto);

    }
}
