package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.entity.DomesticOilEntity;
import com.a701.nongstradamus.data.dto.DomesticOilDto;
import com.a701.nongstradamus.data.mapper.DomesticOilMapper;
import com.a701.nongstradamus.data.repository.DomesticOilRepository;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomesticOilService {

    @Value("${key.domesticOil.url}")
    private String callBackUrl;
    @Value("${key.domesticOil.servicekey}")
    private String serviceKey;
    private String dataType = "json";

    private static JSONArray resultObjects;
    private static LocalDate today;

    private final DomesticOilRepository domesticOilRepository;

    public DomesticOilService(DomesticOilRepository domesticOilRepository) {
        this.domesticOilRepository = domesticOilRepository;
    }

    public DomesticOilDto getDomesticOilByDate(DomesticOilEntity domesticOil) {

        return null;
    }

    public List<DomesticOilDto> getAllDomesticOil() {

        List<DomesticOilEntity> lst = domesticOilRepository.findAll();
        List<DomesticOilDto> result = lst.stream()
                .map(DomesticOilMapper.INSTANCE::fromEntityToDto) // 수정된 부분
                .collect(Collectors.toList());
        return result;
    }

    public DomesticOilDto createDomesticOil(DomesticOilDto dto) {

        DomesticOilEntity entity = DomesticOilMapper.INSTANCE.fromDtoToEntity(dto);

        domesticOilRepository.save(entity);

        return dto;
    }

    public DomesticOilDto updateDomesticOilByDate(DomesticOilDto dto) {

        return null;
    }

    public DomesticOilDto deleteDomesticOilByDate() {

        return null;
    }


    @Scheduled(cron="0/5 * * * * ?") // 매일 12시
    public ResponseEntity<String> callForecastApi(
//            @RequestParam(value="base_time") String baseTime,
//            @RequestParam(value="base_date") String baseDate,
//            @RequestParam(value="beach_num") String beachNum
    ){
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;
        resultObjects = null;

        String urlStr = callBackUrl +
                "?out=" + dataType +
                "&code=" + serviceKey;
        try {
            URL url = new URL(urlStr);

            urlConnection = (HttpURLConnection) url.openConnection();
            stream = getNetworkConnection(urlConnection);
//            result = readStreamToString(stream);

//////////////////////////////////////////////////////////////////////////////

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
//            String jsonString = stringBuilder.toString();
//
//            // Parse JSON string using org.json.JSONObject
//            JSONObject jsonObject = new JSONObject(jsonString);
//            JSONObject resultObject = jsonObject.getJSONObject("RESULT")
//                                                .getJSONArray("OIL").getJSONObject(1);
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//            LocalDate date = LocalDate.parse(resultObject.getString("TRADE_DT"), formatter);
//
//            Double avgPrice = resultObject.getDouble("PRICE");
            String jsonString = stringBuilder.toString();

            // Parse JSON string using org.json.JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray resultObjects = jsonObject.getJSONObject("RESULT").getJSONArray("OIL");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            today = LocalDate.now();

            DomesticOilDto domesticOilDto = new DomesticOilDto(0,
                    resultObjects.getJSONObject(0).getDouble("PRICE"),
                    resultObjects.getJSONObject(1).getDouble("PRICE"),
                    resultObjects.getJSONObject(2).getDouble("PRICE"),
                    today
            );
            System.out.println(createDomesticOil(domesticOilDto));

//            {"TRADE_DT":"20240305","PRODCD":"B027","PRICE":"1639.41","DIFF":"+0.46","PRODNM":"?��발유"}

            // Extract values and populate your DomesticOilDto

//////////////////////////////////////////////////////////////////////////////

            if (stream != null) stream.close();

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /* URLConnection 을 전달받아 연결정보 설정 후 연결, 연결 후 수신한 InputStream 반환 */
    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setConnectTimeout(6000);
        urlConnection.setReadTimeout(6000);
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);

        if(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }

        return urlConnection.getInputStream();
    }

    /* InputStream을 전달받아 문자열로 변환 후 반환 */
    private String readStreamToString(InputStream stream) throws IOException {
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        String readLine;
        while((readLine = br.readLine()) != null) {
            result.append(readLine);
        }

        br.close();

        return result.toString();
    }
}
