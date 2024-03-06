package com.a701.nongstradamus.data.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RecipeDataService {

    @Value("${api.key}")
    private String apiKey;

    WebClient webClient = WebClient.create("https://openapi.foodsafetykorea.go.kr");




    // 매달 1일 0시에 실행
//    @Scheduled(cron = "0 0 0 1 * ?")

    //10초마다 실행(테스트용)
    @Scheduled(fixedDelay = 10000)
    public void updateRecipeData() {
        String result = webClient.get()
            .uri("/api/" + apiKey + "/COOKRCP01/json/1/2")
            .retrieve()
            .bodyToMono(String.class)
            .block();
        //System.out.println(result);

        // JSON 파싱
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            JsonNode rowArray = jsonNode.path("COOKRCP01").path("row");
            for(JsonNode row : rowArray){
                String title = row.path("RCP_NM").asText();
                String ingredient = row.path("RCP_PARTS_DTLS").asText();
                String image = row.path("ATT_FILE_NO_MAIN").asText();
                StringBuilder content = new StringBuilder();
                for (int i = 1; i <= 20; i++) {
                    String manualValue = jsonNode.path("MANUAL" + String.format("%02d", i)).asText();
                    if (!manualValue.isEmpty()) {
                        content.append(manualValue).append("\n");
                    }
                }
                System.out.println(title);
                System.out.println(ingredient);
                System.out.println(image);
                System.out.println(content);


            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(result);
    }

}
