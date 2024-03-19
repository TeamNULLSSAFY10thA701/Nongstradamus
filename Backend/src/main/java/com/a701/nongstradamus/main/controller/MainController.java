package com.a701.nongstradamus.main.controller;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.main.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/main")
public class MainController {

    @Autowired
    private MainService mainService;

    // 가격 하락율이 가장 큰 농산물 조회
    @GetMapping("/best-present")
    public ResponseEntity<CommonDto> findHighestPriceDecreaseCrop() {
        CommonDto response = mainService.findHighestPriceDecreaseCrop();
        return ResponseEntity.ok(response);
    }

    // 가격 상승율이 가장 큰 농산물 조회
    @GetMapping("/best-predict")
    public ResponseEntity<CommonDto> findHighestPriceIncreaseCrop() {
        CommonDto response = mainService.findHighestPriceIncreaseCrop();
        return ResponseEntity.ok(response);
    }

    // 예측 가격 조회
    @GetMapping("/predict/card")
    public ResponseEntity<CommonDto> findPredictCard() {
        CommonDto response = mainService.findPredictCard();
        return ResponseEntity.ok(response);
    }

    // 오늘 가격 조회
    @GetMapping("/present/card")
    public ResponseEntity<CommonDto> findPresentCard() {
        CommonDto response = mainService.findPresentCard();
        return ResponseEntity.ok(response);
    }

}
