package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.RecipeDto;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.entity.WeatherEntity;
import com.a701.nongstradamus.data.entity.WeatherOriginCodeEntity;
import com.a701.nongstradamus.data.mapper.RecipeMapper;
import com.a701.nongstradamus.data.repository.WeatherOriginCodeRepository;
import com.a701.nongstradamus.data.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/*
 * 가정 : 방울토마토만 조사하는 코드입니다.
 * PA_CROP_SPE_ID=PA150101
 */

@Service
@RequiredArgsConstructor
@EnableScheduling
public class WeatherDataServiceImpl implements WeatherDataService {

    private final WeatherRepository weatherRepository;

    private final WeatherOriginCodeRepository weatherOriginCodeRepository;

    @Value("${key.weather.url}")
    private String urlBase;

    @Value("${key.weather.api}")
    private String apiKey;

    //수집하는 농작물의 코드 배열
    private String[] cropIdArr = {"PA150101"};

    //테스트용
    //@Override
    //@Scheduled(fixedDelay = 10000000)

    // 매일 0시에 실행
//    @Override
//    @Scheduled(cron = "0 0 0 * * ?")

    //테스트용
    @Override
    @Scheduled(fixedDelay = 10000000)
    public void updateWeatherData() throws IOException, ParserConfigurationException, SAXException {

        List<WeatherOriginCodeEntity> weatherOriginCodes = weatherOriginCodeRepository.findAll();
        HashMap<Integer, String> codeMap = new HashMap<>();

        for (WeatherOriginCodeEntity weatherOriginCode : weatherOriginCodes) {
            // 여기서 코드가 String이지만, 자동으로 Integer로 변환됩니다.
            codeMap.put(Integer.parseInt(weatherOriginCode.getCode()), weatherOriginCode.getName());
        }

//
//        LocalDate today = LocalDate.now();
//        DateTimeFormatter onlyNumber = DateTimeFormatter.ofPattern("yyyyMMdd");
//
//        //a일전 데이터 검색 후 없으면 등록 -> a-1일전 데이터 검색 -> 최대 100일전까지 등록
//        day:for(int minusDay = 1; minusDay <= 1; minusDay++){
//            LocalDate currentDate  = today.minusDays(minusDay);
//
//            //해당 날짜의 데이터가 있는지 검색
//            List<WeatherEntity> weatherList = weatherRepository.findByDate(currentDate);
///*
//예시코드
//https://apis.data.go.kr/1360000/FmlandWthrInfoService/getDayStatistics
//?serviceKey=co8w9X5v4%2FmQjDcQ0tAkvo8gwnFqsiBT5izaxt8BqCCHWoFAdo%2FXRm9gCmNHafv4wwRHzfGj3GyQHaaWUBgKGw%3D%
//&numOfRows=100
//&pageNo=1
//&ST_YMD=20240307
//&ED_YMD=20240307
//&AREA_ID=999999999
//&PA_CROP_SPE_ID=PA150101
// */
//
//            if(weatherList.isEmpty()){
//                for(String str : cropIdArr){
//                    StringBuilder urlFull = new StringBuilder();
//                    urlFull.append(urlBase).append("?serviceKey=").append(apiKey)
//                        .append("&numOfRows=100")
//                        .append("&pageNo=1")
//                        .append("&ST_YMD=")
//                        .append(currentDate.format(onlyNumber))
//                        .append("&ED_YMD=")
//                        .append(currentDate.format(onlyNumber))
//                        .append("&AREA_ID=999999999")
//                        .append("&PA_CROP_SPE_ID=")
//                        .append(str);
//                    System.out.println(urlFull);
//                    // api 검색
//                    ResponseEntity<String> res = OpenAPIManager.fetchXML(urlFull.toString());
//
//                    // 데이터 파싱
//                    // DocumentBuilderFactory 인스턴스 생성
//                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                    // DocumentBuilder 인스턴스 생성
//                    DocumentBuilder builder = factory.newDocumentBuilder();
//
//                    try {
//                        int tryCount = 0;
//                        NodeList itemList = null;
//
//                        // 최대 5번까지 시도
//                        while (tryCount < 5) {
//                            // XML 문서 파싱하는 코드 (doc 변수를 얻는 부분) 여기에 위치
//                            // 예: Document doc = ...;
//                            Document doc = builder.parse(new InputSource(new StringReader(res.getBody())));
//                            doc.getDocumentElement().normalize();
//
//                            itemList = doc.getElementsByTagName("item");
//                            System.out.println(itemList.getLength());
//
//                            if (itemList.getLength() > 0) {
//                                break; // itemList의 길이가 0이 아니면 반복문 탈출
//                            } else {
//                                // 10초 대기
//                                System.out.println((tryCount + 1) + "번째 시도에서 아이템을 찾지 못했습니다. 10초 후 재시도합니다.");
//                                Thread.sleep(10000); // 10,000밀리초 = 10초
//                                tryCount++; // 시도 횟수 증가
//                            }
//                        }
//
//                        // itemList의 길이가 여전히 0이면 아이템을 찾지 못한 것이므로, 여기서 처리를 중단하거나 사용자에게 알림
//                        if (itemList.getLength() == 0) {
//                            System.out.println("아이템을 찾을 수 없습니다.");
//                            continue day;
//                        }
//
//                        // itemList의 길이만큼 반복하면서 각 item의 areaName과 dayAvgRhm 값을 추출합니다.
//                        for (int i = 0; i < itemList.getLength(); i++) {
//                            Node node = itemList.item(i);
//
//                            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                                Element element = (Element) node;
//
//                                // areaName과 dayAvgRhm 값을 가져옵니다.
//                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                                String ymd = element.getElementsByTagName("ymd").item(0).getTextContent();
//                                LocalDate date = LocalDate.parse(ymd, formatter);
//
//                                String avgTemperature = element.getElementsByTagName("dayAvgTa").item(0).getTextContent();
//                                String maxTemperature = element.getElementsByTagName("dayMaxTa").item(0).getTextContent();
//                                String minTemperature = element.getElementsByTagName("dayMinTa").item(0).getTextContent();
//                                String rain = element.getElementsByTagName("daySumRn").item(0).getTextContent();
//                                String wind = element.getElementsByTagName("dayAvgWs").item(0).getTextContent();
//                                String humidity = element.getElementsByTagName("dayAvgRhm").item(0).getTextContent();
//                                String sunDuration = element.getElementsByTagName("daySumSs").item(0).getTextContent();
//                                String paCropName = element.getElementsByTagName("paCropName").item(0).getTextContent();
//                                String areaName = element.getElementsByTagName("areaName").item(0).getTextContent();
//
//
//
//
//
//
//                                // 가져온 값을 출력합니다.
//                                System.out.println("날짜: " + date + ", 평온: " + avgTemperature
//                                    + ", 맥온: " + maxTemperature+ ", 민온: " + minTemperature
//                                    + ", 강수량: " + rain
//                                    + ", 바람: " + wind
//                                    + ", 평습: " + humidity
//                                    + ", 일조: " + sunDuration+ ", 작물: " + paCropName+ ", 지역: " + areaName
//
//
//                                );
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
////                    try {
////                        // res.getBody()에서 XML 문자열을 가져와 Document로 파싱합니다.
////                        Document doc = builder.parse(new InputSource(new StringReader(res.getBody())));
////                        doc.getDocumentElement().normalize();
////
////                        // "item" 태그를 가진 모든 노드를 NodeList로 가져옵니다.
////                        NodeList itemList = doc.getElementsByTagName("item");
////                        System.out.println(itemList.getLength());
////                        // itemList의 길이만큼 반복하면서 각 item의 areaName과 dayAvgRhm 값을 추출합니다.
////                        for (int i = 0; i < itemList.getLength(); i++) {
////                            Node node = itemList.item(i);
////
////                            if (node.getNodeType() == Node.ELEMENT_NODE) {
////                                Element element = (Element) node;
////
////                                // areaName과 dayAvgRhm 값을 가져옵니다.
////                                String areaName = element.getElementsByTagName("areaName").item(0).getTextContent();
////                                String dayAvgRhm = element.getElementsByTagName("dayAvgRhm").item(0).getTextContent();
////
////                                // 가져온 값을 출력합니다.
////                                System.out.println("지역 이름: " + areaName + ", 일 평균 상대습도: " + dayAvgRhm);
////                            }
////                        }
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
//
//
//
//                }
//            }
//            else{
//                break;
//            }
//        }

    }

}
