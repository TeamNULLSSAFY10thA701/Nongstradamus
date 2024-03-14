package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.RecipeDto;
import com.a701.nongstradamus.data.mapper.RecipeMapper;
import com.a701.nongstradamus.data.repository.RecipeRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/*
5개 더 db에 넣는 코드 작성할것
 */

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
    //@Scheduled(fixedDelay = 10000000)

    // 매년 1월 1일 0시에 실행
    // 최신 데이터 갱신이 2021년이라서 1년에 1번으로 설정했습니다
//    @Override
//    @Scheduled(cron = "0 0 0 1 1 ?")

    // 매년 1월 1일 0시에 실행
    // 최신 데이터 갱신이 2021년이라서 1년에 1번으로 설정했습니다
    @Override
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void updateRecipeData() {

        //테이블에 있는 모든 recipeTitle 리스트에 저장
        //api에서 받은 title이 있으면 저장 안하고, 없으면 저장하는데 사용
        List<String> existingTitles = recipeRepository.findAllTitles();

        //전체 레시피 수 얻으려고 보내는 요청
        StringBuilder urlFull = new StringBuilder();
        urlFull.append(urlBase).append("/api/").append(apiKey).append("/COOKRCP01/json/1/2");
        ResponseEntity<Map> res = OpenAPIManager.fetchJSON(urlFull.toString());

        //전체 레시피 수 초기화
        int recipeCnt = 1;

        //전체 레시피 수 계산
        Map<String, Object> body = res.getBody();
        Map<String, Object> COOKRCP01Map = (Map<String, Object>) body.get("COOKRCP01");

        String total_count = (String) COOKRCP01Map.get("total_count");
        recipeCnt = Integer.parseInt(total_count);

        //api 요청 횟수
        int messagesCnt = ((recipeCnt - 1) / 100) + 1;

        //db에 보낼 dto들 저장하는 리스트
        List<RecipeDto> dtos = new ArrayList<>();

        //api에 요청
        for (int msgOrder = 1; msgOrder <= messagesCnt; msgOrder++) {
            urlFull = new StringBuilder();
            urlFull.append(urlBase).append("/api/").append(apiKey)
                .append("/COOKRCP01/json/")
                .append(1 + (100 * (msgOrder - 1)))
                .append("/").append(100 * msgOrder);
            res = OpenAPIManager.fetchJSON(urlFull.toString());
            body = res.getBody();
            COOKRCP01Map = (Map<String, Object>) body.get("COOKRCP01");

            List<Map<String, Object>> rowList = (List<Map<String, Object>>) COOKRCP01Map.get("row");

            for (Map<String, Object> row : rowList) {
                String title = (String) row.get("RCP_NM");
                String ingredient = (String) row.get("RCP_PARTS_DTLS");
                String image = (String) row.get("ATT_FILE_NO_MAIN");
                StringBuilder content = new StringBuilder();
                for (int i = 1; i <= 20; i++) {
                    String manualValue;
                    if (i < 10) {
                        manualValue = (String) row.get("MANUAL0" + i);
                    } else {
                        manualValue = (String) row.get("MANUAL" + i);
                    }
                    if (!manualValue.isEmpty()) {
                        //레시피 순서 사이마다 "\n"삽입, 프론트 요청시 변경 가능
                        content.append(manualValue).append("\n");
                    }
                }
                double energy = Double.parseDouble((String) row.get("INFO_ENG"));
                double carbohydrate = Double.parseDouble((String) row.get("INFO_CAR"));
                double protein = Double.parseDouble((String) row.get("INFO_PRO"));
                double fat = Double.parseDouble((String) row.get("INFO_FAT"));
                double natrium = Double.parseDouble((String) row.get("INFO_NA"));

                //지금 얻은 레시피와 같은 제목이 db에 없는경우 저장
                if(!existingTitles.contains(title)){
                    System.out.println(title);
                    RecipeDto dto = new RecipeDto();
                    dto.setTitle(title);
                    dto.setIngredient(ingredient);
                    dto.setImage(image);
                    dto.setContent(String.valueOf(content));
                    dto.setEnergy(energy);
                    dto.setCarbohydrate(carbohydrate);
                    dto.setProtein(protein);
                    dto.setFat(fat);
                    dto.setNatrium(natrium);
                    dtos.add(dto);
                }
            }
        }

        //테이블에 저장
        recipeRepository.saveAll(dtos.stream().map(RecipeMapper.INSTANCE::fromDtoToEntity).collect(
            Collectors.toList()));
        System.out.println(123123);
    }}
