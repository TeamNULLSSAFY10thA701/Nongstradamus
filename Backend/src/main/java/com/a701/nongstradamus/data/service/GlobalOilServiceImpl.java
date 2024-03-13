package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.GlobalOilDto;
import com.a701.nongstradamus.data.entity.GlobalOilEntity;
import com.a701.nongstradamus.data.mapper.GlobalOilMapper;
import com.a701.nongstradamus.data.repository.GlobalOilRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class GlobalOilServiceImpl implements GlobalOilService {

    @Value("${key.globalOil.url}")
    private String callBackUrl;
    @Value("${key.globalOil.servicekey}")
    private String serviceKey;
    private final GlobalOilRepository globalOilRepository;
    @Override
    public void createGlobalOil(GlobalOilDto dto) {

        GlobalOilEntity entity = GlobalOilMapper.INSTANCE.fromDtoToEntity(dto);

        globalOilRepository.save(entity);

    }

    @Override
    @Scheduled(cron = "0 0 0 * * *") // 매일 자정
    public void callGlobalOilScheduler() throws JsonProcessingException {
        // 7일 전의 유가를 가져옴
        String today = LocalDate.now().minusDays(7).toString();

        // 요청 url 주소
        String urlStr = callBackUrl + "?frequency=daily&data[0]=value&start="
                + today + "&end=" + today
                + "&sort[0][column]=period&sort[0][direction]=desc&offset=0&length=5000&api_key="
                + serviceKey;

        JSONObject jsonObject = OpenAPIManager.fetchData(urlStr);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonObject.toString());
        JsonNode nestedNode = rootNode.get("response").get("data");

        Map<String, JsonNode> nodeMap = new HashMap<>();
        for (JsonNode entry : nestedNode) {
            nodeMap.put(entry.get("series").asText(), entry);
        }

        if (!nodeMap.isEmpty()) {  // 데이터가 없으면 저장하지 않음
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