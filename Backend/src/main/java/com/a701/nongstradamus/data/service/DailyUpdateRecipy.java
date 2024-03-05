package com.a701.nongstradamus.data.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DailyUpdateRecipy {

    @Value("${API_KEY}")
    private String apiKey;

    WebClient webClient = WebClient.create("https://openapi.foodsafetykorea.go.kr");

    // 매달 1일 0시에 실행
//    @Scheduled(cron = "0 0 0 1 * ?")
    @Scheduled(fixedDelay = 10000)
    public void dailyUpdate() {
        String result = webClient.get()
            .uri("/api/" + apiKey + "/COOKRCP01/json/1/2")
            .retrieve()         // HTTP 요청을 시작하고 응답을 가져옴
            .bodyToMono(String.class)  // 응답의 본문을 Mono로 변환
            .block();
        // 여기에 데이터베이스 업데이트 로직을 작성
        // 예를 들어, JPA를 사용하여 엔터티를 가져와 업데이트하는 코드를 작성
        // EntityManager 또는 JpaRepository를 사용하는 방법을 선택할 수 있습니다.

    }

}
