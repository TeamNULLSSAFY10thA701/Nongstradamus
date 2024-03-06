package com.a701.nongstradamus.data.controller;

import com.a701.nongstradamus.data.domain.DomesticOilDto;
import com.a701.nongstradamus.data.service.DomesticOilService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

@RestController
@RequestMapping("/oil")
public class DomesticOilController {

    @Value("${key.oil.url}")
    private String callBackUrl;
    @Value("${key.oil.servicekey}")
    private String serviceKey;
    private String dataType = "json";

    private final DomesticOilService oilService;

    public DomesticOilController(DomesticOilService oilService) {
        this.oilService = oilService;
    }


    @Scheduled(cron="0/5 * * * * ?")
    @GetMapping("/price")
    public ResponseEntity<String> callForecastApi(
//            @RequestParam(value="base_time") String baseTime,
//            @RequestParam(value="base_date") String baseDate,
//            @RequestParam(value="beach_num") String beachNum
    ){
        HttpURLConnection urlConnection = null;
        InputStream stream = null;
        String result = null;

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
            String jsonString = stringBuilder.toString();

            // Parse JSON string using org.json.JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject resultObject = jsonObject.getJSONObject("RESULT")
                                                .getJSONArray("OIL").getJSONObject(1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate date = LocalDate.parse(resultObject.getString("TRADE_DT"), formatter);

            Double avgPrice = resultObject.getDouble("PRICE");


//            {"TRADE_DT":"20240305","PRODCD":"B027","PRICE":"1639.41","DIFF":"+0.46","PRODNM":"?��발유"}

            // Extract values and populate your DomesticOilDto

            DomesticOilDto domesticOilDto = new DomesticOilDto();
            domesticOilDto.setDomesticOilPriceId(1);
            domesticOilDto.setAvgPrice(avgPrice);
            domesticOilDto.setDate(date);
            System.out.println(oilService.createDomesticOil(domesticOilDto));

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
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
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

//    private JsonObject getJson(InputStream stream) throws IOException {
//        StringBuilder result = new StringBuilder();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
//
//        String readLine;
//        while((readLine = br.readLine()) != null) {
//
//            result.append(readLine);
//        }
//        stream.close();
//
//        result = new JsonObject(result.toString());
//    }
}