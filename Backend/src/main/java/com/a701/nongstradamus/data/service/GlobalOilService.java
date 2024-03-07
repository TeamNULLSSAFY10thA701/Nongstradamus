package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.entity.GlobalOilEntity;
import com.a701.nongstradamus.data.dto.GlobalOilDto;
import com.a701.nongstradamus.data.mapper.GlobalOilMapper;
import com.a701.nongstradamus.data.repository.GlobalOilRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GlobalOilService {

    @Value("${key.globalOil.url}")
    private String callBackUrl;
    @Value("${key.globalOil.servicekey}")
    private String serviceKey;
    private final GlobalOilRepository globalOilRepository;
    private String dataType = "json";
    private Double sumValue;
    private JsonNode targetNode;
    private Map<String, JsonNode> nodeMap;

    public GlobalOilService(GlobalOilRepository globalOilRepository) {
        this.globalOilRepository = globalOilRepository;
    }

    public List<GlobalOilDto> getAllGlobalOil() {

        List<GlobalOilEntity> lst = globalOilRepository.findAll();
        List<GlobalOilDto> result = lst.stream()
                .map(GlobalOilMapper.INSTANCE::fromEntityToDto)
                .collect(Collectors.toList());
        return result;
    }

    public GlobalOilDto createGlobalOil(GlobalOilDto dto) {

        GlobalOilEntity entity = GlobalOilMapper.INSTANCE.fromDtoToEntity(dto);

        globalOilRepository.save(entity);

        return dto;
    }

     @Scheduled(cron="0 0 12 * * ?") // 매일 12시
    public void callForecastApi(){
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;
        // 7일 전의 유가를 가져옴
        String today = LocalDate.now().minusDays(7).toString();

        // 요청 url 주소
        String urlStr = callBackUrl + "?frequency=daily&data[0]=value&start="
                + today + "&end=" + today
                + "&sort[0][column]=period&sort[0][direction]=desc&offset=0&length=5000&api_key="
                + serviceKey;
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

            // ObjectMapper 생성
            ObjectMapper objectMapper = new ObjectMapper();

            // JSON을 Jackson의 JsonNode로 파싱
            JsonNode rootNode = objectMapper.readTree(jsonString);

            // 루트 노드 검색
            targetNode = null;
            targetNode = rootNode.get("response").get("data");

            // hashmap으로 저장
            nodeMap = new HashMap<>();
            for (JsonNode element : targetNode) {
                nodeMap.put(element.get("series").asText(), element);
            }

            if (stream != null) stream.close();

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        if (nodeMap != null) {  // 데이터가 없으면 저장하지 않음
            GlobalOilDto dto = new GlobalOilDto(0,
                    nodeMap.get("RWTC").get("value").asDouble(),
                    nodeMap.get("RBRTE").get("value").asDouble(),
                    nodeMap.get("EER_EPD2DXL0_PF4_RGC_DPG").get("value").asDouble(),
                    nodeMap.get("EER_EPMRU_PF4_RGC_DPG").get("value").asDouble(),
                    LocalDate.now()
            );
            createGlobalOil(dto);
        }
    }
}
