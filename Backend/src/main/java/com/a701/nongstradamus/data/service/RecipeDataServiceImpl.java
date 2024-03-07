package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.RecipeDto;
import com.a701.nongstradamus.data.mapper.RecipeMapper;
import com.a701.nongstradamus.data.repository.RecipeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class RecipeDataServiceImpl implements RecipeDataService {

    private final RecipeRepository recipeRepository;

    @Value("${key.recipe.url}")
    private String urlBase;

    @Value("${key.recipe.api}")
    private String apiKey;

    //테스트용
    //@Override
    //Scheduled(fixedDelay = 10000000)

    // 매년 1월 1일 0시에 실행
    // 최신 데이터 갱신이 2021년이라서 1년에 1번으로 설정했습니다
//    @Override
//    @Scheduled(cron = "0 0 0 1 1 ?")
    @Override
    @Scheduled(fixedDelay = 10000000)
    public void updateRecipeData(){

        //지우고 새로 다 받는방식
        recipeRepository.deleteAll();

        //전체 레시피 수 얻으려고 보내는 요청
        StringBuilder urlFull = new StringBuilder();
        urlFull.append(urlBase).append("/api/").append(apiKey).append("/COOKRCP01/json/1/2");
        ResponseEntity<String> res = OpenAPIManager.fetch(urlFull.toString());

        //전체 레시피 수 초기화
        int recipeCnt = 1;

        //전체 레시피 수 계산
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(res.getBody());

            recipeCnt = Integer.parseInt(jsonNode.path("COOKRCP01").path("total_count").asText());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //api 요청 횟수
        int messagesCnt = ((recipeCnt - 1) / 100) + 1;

        //db에 보낼 dto들 저장하는 리스트
        List<RecipeDto> dtos = new ArrayList<>();

        //api에 요청
        for(int msgOrder = 1; msgOrder <= messagesCnt; msgOrder++) {
            urlFull = new StringBuilder();
            urlFull.append(urlBase).append("/api/").append(apiKey)
                .append("/COOKRCP01/json/")
                .append(1 + (100 * (msgOrder - 1)))
                .append("/").append(100 * msgOrder);
            res = OpenAPIManager.fetch(urlFull.toString());

            //JSON 파싱
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res.getBody());
                JsonNode rowArray = jsonNode.path("COOKRCP01").path("row");

                for (JsonNode row : rowArray) {
                    String title = row.path("RCP_NM").asText();
                    String ingredient = row.path("RCP_PARTS_DTLS").asText();
                    String image = row.path("ATT_FILE_NO_MAIN").asText();

                    StringBuilder content = new StringBuilder();
                    for (int i = 1; i <= 20; i++) {
                        String manualValue;
                        if (i < 10) {
                            manualValue = row.path("MANUAL0" + i).asText();
                        } else {
                            manualValue = row.path("MANUAL" + i).asText();
                        }
                        if (!manualValue.isEmpty()) {
                            //레시피 순서 사이마다 "\n"삽입, 프론트 요청시 변경 가능
                            content.append(manualValue).append("\n");
                        }
                    }

                    //파싱한 값들 dto에 저장
                    RecipeDto dto = new RecipeDto();
                    dto.setTitle(title);
                    dto.setIngredient(ingredient);
                    dto.setImage(image);
                    dto.setContent(String.valueOf(content));
                    dtos.add(dto);
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        //테이블에 저장
        recipeRepository.saveAll(dtos.stream().map(RecipeMapper.INSTANCE::fromDtoToEntity).collect(
            Collectors.toList()));
    }
}
