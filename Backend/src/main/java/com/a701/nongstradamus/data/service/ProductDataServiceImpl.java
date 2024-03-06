package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.data.repository.ProductRepository;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class ProductDataServiceImpl implements ProductDataService{

    private final ProductRepository productRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    @Value("${key.product.url}")
    private String urlStr;

    @Value("${key.product.id}")
    private String id;

    @Value("${key.product.password}")
    private String password;

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateProductData() throws IOException {
        // 검색할 품목을 가져옴
        List<ProductEntity> productList = productRepository.findAll();
        for(ProductEntity product : productList) {
            // oopen api 검색 url 생성
            StringBuilder urlFull = new StringBuilder();
            urlFull.append(urlStr).append("?id=").append(id).append("&password=").append(password); // url, id, password
            LocalDate today = LocalDate.now().minusDays(1);
            LocalDate yesterday = today.minusDays(1);
            LocalDate lastYear = today.minusYears(1);
            urlFull.append("&p_ymd=").append(today.format(DateTimeFormatter.ofPattern("yyyyMMdd"))) // 어제 날짜
                .append("&p_jymd=").append(yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"))) // 그제 날짜
                .append("&d_cd=2") // 청과
                .append("&p_jjymd=").append(lastYear.format(DateTimeFormatter.ofPattern("yyyyMMdd"))) // 지난해 날짜
                .append("&p_pos_gubun=1") // 가락시장
                .append("&pum_nm=").append(product.getName()); // 품목명
            // api 검색
            URL url = new URL(urlFull.toString());
            URLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // 단위가 없다면 단위 검색 후 저장
            if(product.getUnit().isEmpty()) {

            }
            // 데이터 저장
        }
    }

}
