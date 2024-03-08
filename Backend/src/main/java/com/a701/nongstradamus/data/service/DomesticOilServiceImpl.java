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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
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
    @Scheduled(cron="0 0 12 * * ?") // 매일s 12시
    public void callForecastApi(){
        HttpURLConnection urlConnection = null;
        InputStream stream;
        String result = null;
        LocalDate today = LocalDate.now();
        String dataType = "json";

        String urlStr = callBackUrl +
                "?out=" + dataType +
                "&code=" + serviceKey;

        try {
            ResponseEntity<String> response = OpenAPIManager.fetchXML(urlStr);

            stream = new ByteArrayInputStream(response.getBody().getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String jsonString = stringBuilder.toString();

            // Parse JSON string using org.json.JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray resultObjects = jsonObject.getJSONObject("RESULT").getJSONArray("OIL");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            DomesticOilDto domesticOilDto = new DomesticOilDto(0,
                    resultObjects.getJSONObject(0).getDouble("PRICE"),
                    resultObjects.getJSONObject(1).getDouble("PRICE"),
                    resultObjects.getJSONObject(2).getDouble("PRICE"),
                    today
            );
            createDomesticOil(domesticOilDto);

            stream.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
