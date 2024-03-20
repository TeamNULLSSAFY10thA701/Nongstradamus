package com.a701.nongstradamus.main.service;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.data.entity.PriceHistoryEntity;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.main.dto.ProductInfoDto;
import com.a701.nongstradamus.main.entity.PricePredictEntity;
import com.a701.nongstradamus.main.repository.PricePredictRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@EnableScheduling //테스트
public class MainServiceImpl implements MainService{

    private final PricePredictRepository pricePredictRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    @Override
    @Transactional(readOnly=true)
    @Scheduled(fixedDelay = 10000000) //테스트용
    public CommonDto findHighestRatioDecreaseProduct() throws EntityNotFoundException {

        CommonDto response = new CommonDto();
        ProductInfoDto dto = new ProductInfoDto();

        List<PricePredictEntity> pricePredictList = pricePredictRepository.findAllByDate(LocalDate.now().plusDays(1));

        if(pricePredictList.isEmpty()){
            response.setMsg("가격 하락율이 가장 클 농산물을 조회할 수 없습니다");
            throw new EntityNotFoundException();
        }

        PricePredictEntity highestPriceDecreaseProduct = null;
        double highestRatio = 100000;

        for (PricePredictEntity pricePredict : pricePredictList) {
            if (pricePredict.getGrade()==2 && pricePredict.getRatio() < highestRatio) {
                highestRatio = pricePredict.getRatio();
                highestPriceDecreaseProduct = pricePredict;
            }
        }

        dto.setName(highestPriceDecreaseProduct.getProduct().getName());
        dto.setPrice(highestPriceDecreaseProduct.getPrice());
        dto.setRatio(highestPriceDecreaseProduct.getRatio());
        dto.setNickname(highestPriceDecreaseProduct.getProduct().getNickname());

//        System.out.println(dto);//테스트

        response.setData(dto);
        response.setMsg("good");
        response.setCode(200);

        return response;
    }


    @Override
    @Transactional(readOnly=true)
    @Scheduled(fixedDelay = 10000000) //테스트용
    public CommonDto findHighestRatioIncreaseProduct() throws EntityNotFoundException {

        CommonDto response = new CommonDto();
        ProductInfoDto dto = new ProductInfoDto();

        List<PricePredictEntity> pricePredictList = pricePredictRepository.findAllByDate(LocalDate.now().plusDays(1));
        if(pricePredictList.isEmpty()){
            response.setMsg("가격 상승율이 가장 클 농산물을 조회할 수 없습니다");
            throw new EntityNotFoundException();
        }

        PricePredictEntity highestPriceIncreaseProduct = null;
        double highestRatio = -100000;

        for (PricePredictEntity pricePredict : pricePredictList) {
            if (pricePredict.getGrade()==2 && pricePredict.getRatio() > highestRatio) {
                highestRatio = pricePredict.getRatio();
                highestPriceIncreaseProduct = pricePredict;
            }
        }

        dto.setName(highestPriceIncreaseProduct.getProduct().getName());
        dto.setPrice(highestPriceIncreaseProduct.getPrice());
        dto.setRatio(highestPriceIncreaseProduct.getRatio());
        dto.setNickname(highestPriceIncreaseProduct.getProduct().getNickname());

//        System.out.println(dto);//테스트


        response.setData(dto);
        response.setMsg("good");
        response.setCode(200);

        return response;
    }

    @Override
    @Transactional(readOnly=true)
    @Scheduled(fixedDelay = 10000000) //테스트용
    public CommonDto findPredictCard() throws EntityNotFoundException {

        CommonDto response = new CommonDto();

        List<PricePredictEntity> pricePredictList = pricePredictRepository.findAllByDate(LocalDate.now().plusDays(1));

        if(pricePredictList.isEmpty()){
            response.setMsg("농산물의 1일 뒤 예상 가격을 조회할 수 없습니다");
            throw new EntityNotFoundException();
        }

        List<ProductInfoDto> predictList = new ArrayList<>();

        for (PricePredictEntity pricePredict : pricePredictList) {
            if(pricePredict.getGrade()==2){
                ProductInfoDto dto = new ProductInfoDto();
                dto.setName(pricePredict.getProduct().getName());
                dto.setPrice(pricePredict.getPrice());
                dto.setRatio(pricePredict.getRatio());
                dto.setNickname(pricePredict.getProduct().getNickname());
                predictList.add(dto);
//                System.out.println(dto); //테스트
            }
        }

        response.setData(predictList);
        response.setMsg("good");
        response.setCode(200);

        return response;
    }

    @Override
    @Transactional(readOnly=true)
    @Scheduled(fixedDelay = 10000000) //테스트용
    public CommonDto findPresentCard() throws EntityNotFoundException {

        CommonDto response = new CommonDto();

        List<PricePredictEntity> pricePresentList = pricePredictRepository.findAllByDate(LocalDate.now());

        if(pricePresentList.isEmpty()){
            response.setMsg("농산물의 오늘의 예상 가격을 조회할 수 없습니다");
            throw new EntityNotFoundException();
        }

        List<ProductInfoDto> presentList;
        presentList = pricePresentList.stream().filter(pricePredictEntity -> (
            pricePredictEntity.getGrade()==2
        )).map(entity->{
            return new ProductInfoDto(entity.getProduct().getName(), entity.getPrice(), entity.getRatio(), entity.getProduct().getNickname());
        }).collect(Collectors.toList());

//        for(ProductInfoDto p : presentList){
//            System.out.println(p);
//        } //테스트

        response.setData(presentList);
        response.setMsg("good");
        response.setCode(200);

        return response;
    }

    @Override
    @Transactional(readOnly=true)
    @Scheduled(fixedDelay = 10000000) //테스트용
    public CommonDto findPastCard() throws EntityNotFoundException {
        CommonDto response = new CommonDto();

        LocalDate localDate = LocalDate.now().minusDays(7);
        Date date = java.sql.Date.valueOf(localDate);

        List<PriceHistoryEntity> priceHistoryList = priceHistoryRepository.findAllByDate(date);

        if(priceHistoryList.isEmpty()){
            response.setMsg("농산물의 7일 전 예상 가격을 조회할 수 없습니다");
            throw new EntityNotFoundException();
        }

        List<ProductInfoDto> pastList = new ArrayList<>();

        for (PriceHistoryEntity pricePast : priceHistoryList) {
            if(pricePast.getGrade()==2){
                ProductInfoDto dto = new ProductInfoDto();
                dto.setName(pricePast.getProduct().getName());
                dto.setPrice(pricePast.getPrice());
                dto.setRatio(pricePast.getRatio());
                dto.setNickname(pricePast.getProduct().getNickname());
                pastList.add(dto);
//                System.out.println(dto); //테스트
            }
        }

        response.setData(pastList);
        response.setMsg("good");
        response.setCode(200);

        return response;
    }
}
