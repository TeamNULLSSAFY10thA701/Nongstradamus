package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.common.OpenAPIManager;
import com.a701.nongstradamus.data.entity.DomesticOilEntity;
import com.a701.nongstradamus.data.dto.DomesticOilDto;
import com.a701.nongstradamus.data.mapper.DomesticOilMapper;
import com.a701.nongstradamus.data.repository.DomesticOilRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
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

    @Scheduled(cron="0/5 * * * * ?") // 매일 12시
    public void callForecastApi(){
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;
        resultObjects = null;

        String urlStr = callBackUrl +
                "?out=" + dataType +
                "&code=" + serviceKey;

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

            if (stream != null) stream.close();

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        new ResponseEntity<>(result, HttpStatus.OK);
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
