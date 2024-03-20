package com.a701.nongstradamus.price.controller;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.price.service.AllService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/all")
public class AllController {

    private final AllService allService;

    @GetMapping("/{category-id}")
    public ResponseEntity<CommonDto> productList(@PathVariable("category-id")Integer category){
        return new ResponseEntity<CommonDto>(allService.findProducts(category), HttpStatus.OK);
    }
}
