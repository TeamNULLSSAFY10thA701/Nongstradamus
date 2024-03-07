package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.entity.RecipeEntity;
import com.a701.nongstradamus.data.repository.RecipeRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RecipeDataService {

    @PersistenceContext
    private EntityManager entityManager;
    RecipeRepository recipeRepository;

    @Autowired
    public RecipeDataService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Value("${api.key}")
    private String apiKey;

    WebClient webClient = WebClient.create("https://openapi.foodsafetykorea.go.kr");

    // 매년 1월 1일 0시에 실행
    // 최신 갱신이 2021년이라서 1년에 1번으로 설정했습니다
//    @Scheduled(cron = "0 0 0 1 1 ?")

    //100초마다 실행(테스트용)
    @Scheduled(fixedDelay = 100000)
    @Transactional
    public void updateRecipeData() {

        //지우고 새로 다 받는방식
        recipeRepository.deleteAll();

        //전체 레시피 수 얻으려고 보내는 요청
        String getRecipeCnt = webClient.get()
            .uri("/api/" + apiKey + "/COOKRCP01/json/1/2")
            .retrieve()
            .bodyToMono(String.class)
            .block();

        int recipeCnt = 1;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(getRecipeCnt);
            //전체 레시피 수
            recipeCnt = Integer.parseInt(jsonNode.path("COOKRCP01").path("total_count").asText());
            System.out.println("recipeCnt");
            System.out.println(recipeCnt);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //보내는 요청 수
        int messagesCnt = ((recipeCnt - 1) / 100) + 1;

        //한번에 100개씩 받습니다
        for(int msgOrder = 1; msgOrder <= messagesCnt; msgOrder++){
            String result = webClient.get()
                .uri("/api/" + apiKey + "/COOKRCP01/json/"+ (1 + (100 * (msgOrder-1))) + "/" + 100 * msgOrder)
                .retrieve()
                .bodyToMono(String.class)
                .block();

            //JSON 파싱
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
                        String manualValue;
                        if (i < 10){
                            manualValue = row.path("MANUAL0" + i).asText();
                        }
                        else{
                            manualValue = row.path("MANUAL" + i).asText();
                        }
                        if (!manualValue.isEmpty()) {
                            //레시피 순서 사이마다 "\n"삽입, 프론트 요청시 변경 가능
                            content.append(manualValue).append("\n");
                        }
                    }

                    RecipeEntity recipeEntity = new RecipeEntity(title, ingredient, image, String.valueOf(content));
                    entityManager.persist(recipeEntity);
                    recipeRepository.save(recipeEntity);
                }
            }

            catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}