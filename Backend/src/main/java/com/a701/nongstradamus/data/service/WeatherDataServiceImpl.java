package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.dto.RecipeDto;
import com.a701.nongstradamus.data.dto.WeatherDto;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.entity.WeatherEntity;
import com.a701.nongstradamus.data.entity.WeatherOriginCodeEntity;
import com.a701.nongstradamus.data.mapper.RecipeMapper;
import com.a701.nongstradamus.data.mapper.WeatherMapper;
import com.a701.nongstradamus.data.mapper.WholesaleMarketMapper;
import com.a701.nongstradamus.data.repository.ProductRepository;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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

@Service
@RequiredArgsConstructor
@EnableScheduling
public class WeatherDataServiceImpl implements WeatherDataService {

    private final ProductRepository productRepository;

    private final WeatherRepository weatherRepository;

    private final WeatherOriginCodeRepository weatherOriginCodeRepository;

    @Value("${key.weather.url}")
    private String urlBase;

    @Value("${key.weather.key}")
    private String apiKey;

    //테스트용
    //@Override
    //@Scheduled(fixedDelay = 10000000)

    // 매일 0시에 실행
//    @Override
//    @Scheduled(cron = "0 0 0 * * ?")

    // 매일 0시에 실행
    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateWeatherData() throws IOException, ParserConfigurationException, SAXException {

        List<WeatherOriginCodeEntity> weatherOriginCodes = weatherOriginCodeRepository.findAll();

        List<ProductEntity> products = productRepository.findAll();

        LocalDate today = LocalDate.now();
        DateTimeFormatter onlyNumber = DateTimeFormatter.ofPattern("yyyyMMdd");
        List<WeatherDto> dtos = new ArrayList<>();

        // 오늘-1일부터 검색, 해당 날짜 데이터 없으면 해당 날짜 -1일 검색, 최대 100일
        day: for (int minusDay = 2; minusDay <= 3; minusDay++) {

            //검색일자
            LocalDate searchDate = today.minusDays(minusDay);

            //해당 날짜의 데이터가 있는지 검색
            List<WeatherEntity> weatherList = weatherRepository.findByDate(searchDate);

            if (weatherList.isEmpty()) {
                for (ProductEntity product : products) {
                    String productCode = product.getWeatherCode(); //주산지 api 주소에 넣을 식품 코드
                    StringBuilder urlFull = new StringBuilder();
                    urlFull.append(urlBase).append("?serviceKey=").append(apiKey)
                        .append("&numOfRows=100")
                        .append("&pageNo=1")
                        .append("&ST_YMD=")
                        .append(searchDate.format(onlyNumber))
                        .append("&ED_YMD=")
                        .append(searchDate.format(onlyNumber))
                        .append("&AREA_ID=999999999")
                        .append("&PA_CROP_SPE_ID=")
                        .append(productCode);
                    System.out.println(urlFull.toString());

                    ResponseEntity<String> res = OpenAPIManager.fetchXML(urlFull.toString());

                    // 데이터 파싱
                    // DocumentBuilderFactory 인스턴스 생성
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    // DocumentBuilder 인스턴스 생성
                    DocumentBuilder builder = factory.newDocumentBuilder();

                    try {
                        Document doc = builder.parse(
                            new InputSource(new StringReader(res.getBody())));
                        doc.getDocumentElement().normalize();

                        NodeList itemList = doc.getElementsByTagName("item");
                        System.out.println(itemList.getLength());

                        //지역코드가 이전과 같은 경우 같은 것들 평균내서 데이터 하나로 만든다
                        String beforeAreaId = "00";

                        //지역코드가 같은 데이터의 갯수
                        int stack = 1;

                        int avgTemperatureSum = 0;
                        int maxTemperatureSum = 0;
                        int minTemperatureSum = 0;
                        int rainSum = 0;
                        int windSum = 0;
                        int humiditySum = 0;
                        int sunDurationSum = 0;


                        List<Node> nodeList = new ArrayList<>();

                        for (int i = 0; i < itemList.getLength(); i++) {
                            Node node = itemList.item(i);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                nodeList.add(node);
                            }
                        }

                        Collections.sort(nodeList, (node1, node2) -> {
                            Element element1 = (Element) node1;
                            Element element2 = (Element) node2;

                            // areaId 값을 가져와서 앞 두 글자를 추출하여 비교
                            String areaId1 = element1.getElementsByTagName("areaId").item(0).getTextContent().substring(0, 2);
                            String areaId2 = element2.getElementsByTagName("areaId").item(0).getTextContent().substring(0, 2);

                            // 문자열을 정수로 변환하여 비교
                            return Integer.parseInt(areaId1) - Integer.parseInt(areaId2);
                        });


                        for(Node node : nodeList){
                                Element element = (Element) node;

                                // areaName과 dayAvgRhm 값을 가져옵니다.
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                    "yyyy-MM-dd HH:mm:ss");
                                String ymd = element.getElementsByTagName("ymd").item(0)
                                    .getTextContent();
                                LocalDate date = LocalDate.parse(ymd, formatter);

                                String avgTemperature = element.getElementsByTagName("dayAvgTa")
                                    .item(0).getTextContent();
                                String maxTemperature = element.getElementsByTagName("dayMaxTa")
                                    .item(0).getTextContent();
                                String minTemperature = element.getElementsByTagName("dayMinTa")
                                    .item(0).getTextContent();
                                String rain = element.getElementsByTagName("daySumRn").item(0)
                                    .getTextContent();
                                String wind = element.getElementsByTagName("dayAvgWs").item(0)
                                    .getTextContent();
                                String humidity = element.getElementsByTagName("dayAvgRhm").item(0)
                                    .getTextContent();
                                String sunDuration = element.getElementsByTagName("daySumSs")
                                    .item(0).getTextContent();

                                String areaId = element.getElementsByTagName("areaId").item(0)
                                    .getTextContent().substring(0,2);

                                avgTemperatureSum += Integer.parseInt(avgTemperature);
                                maxTemperatureSum += Integer.parseInt(maxTemperature);
                                minTemperatureSum += Integer.parseInt(minTemperature);
                                rainSum += Integer.parseInt(rain);
                                windSum += Integer.parseInt(wind);
                                humiditySum += Integer.parseInt(humidity);
                                sunDurationSum += Integer.parseInt(sunDuration);

                                if(areaId.equals(beforeAreaId)){
                                    stack++;
                                }
                                else{
                                    WeatherDto dto = new WeatherDto();
                                    dto.setDate(date);
                                    dto.setAvgTemperature(avgTemperatureSum/stack);
                                    dto.setMaxTemperature(maxTemperatureSum/stack);
                                    dto.setMinTemperature(minTemperatureSum/stack);
                                    dto.setRain(rainSum/stack);
                                    dto.setWind(windSum/stack);
                                    dto.setHumidity(humiditySum/stack);
                                    dto.setSunDuration(sunDurationSum/stack);
                                    dto.setProduct(product);
                                    for(WeatherOriginCodeEntity w : weatherOriginCodes){
                                        if(areaId.equals(w.getCode())){
                                            dto.setOrigin(w);
                                        }
                                    }
                                    dtos.add(dto);
                                    beforeAreaId = areaId;

                                    //초기화
                                    avgTemperatureSum = 0;
                                    maxTemperatureSum = 0;
                                    minTemperatureSum = 0;
                                    rainSum = 0;
                                    windSum = 0;
                                    humiditySum = 0;
                                    sunDurationSum = 0;

                                }
                            }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        weatherRepository.saveAll(dtos.stream().map(WeatherMapper.INSTANCE::fromDtoToEntity).collect(
            Collectors.toList()));
        System.out.println(123123);
    }
}