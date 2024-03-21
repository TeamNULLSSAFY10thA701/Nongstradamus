package com.a701.nongstradamus.main.controller;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.main.service.MainService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/main")
public class MainController {

    @Autowired
    private MainService mainService;

    // 가격 하락율이 가장 클 농산물 조회
    @GetMapping("/best-decrease")
    public ResponseEntity<CommonDto> findHighestRatioDecreaseProduct() {
        CommonDto response = mainService.findHighestRatioDecreaseProduct();
        return ResponseEntity.ok(response);
    }

    // 가격 상승율이 가장 큰 농산물 조회
    @GetMapping("/best-increase")
    public ResponseEntity<CommonDto> findHighestRatioIncreaseProduct() throws EntityNotFoundException {
        CommonDto response = mainService.findHighestRatioIncreaseProduct();
        return ResponseEntity.ok(response);
    }

    // 내일 가격 조회
    @GetMapping("/predict/card")
    public ResponseEntity<CommonDto> findPredictCard() throws EntityNotFoundException {
        CommonDto response = mainService.findPredictCard();
        return ResponseEntity.ok(response);
    }

    // 오늘 가격 조회
    @GetMapping("/present/card")
    public ResponseEntity<CommonDto> findPresentCard() throws EntityNotFoundException {
        CommonDto response = mainService.findPresentCard();
        return ResponseEntity.ok(response);
    }

    // 지난주 가격 조회
    @GetMapping("/past/card")
    public ResponseEntity<CommonDto> findPastCard() {
        CommonDto response = mainService.findPastCard();
        return ResponseEntity.ok(response);
    }
}
