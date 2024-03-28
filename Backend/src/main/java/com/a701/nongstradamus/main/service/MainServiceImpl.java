package com.a701.nongstradamus.main.service;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.data.entity.PriceHistoryEntity;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.main.dto.ProductInfoDto;
import com.a701.nongstradamus.main.entity.PricePredictEntity;
import com.a701.nongstradamus.main.repository.PricePredictRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class MainServiceImpl implements MainService{

    private final PricePredictRepository pricePredictRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    private CommonDto highestRatioDecreadeProduct;

    private CommonDto highestRatioIncreaseProduct;

    private CommonDto predictCards;

    @Override
    @Transactional(readOnly=true)
    public CommonDto findHighestRatioDecreaseProduct() throws EntityNotFoundException {

        if(highestRatioDecreadeProduct != null){
            return highestRatioDecreadeProduct;
        }

        CommonDto response = new CommonDto();
        ProductInfoDto dto = new ProductInfoDto();

        List<PricePredictEntity> pricePredictList = pricePredictRepository.findAllByDate(LocalDate.now().plusDays(1));

        if(pricePredictList.isEmpty()){
            throw new EntityNotFoundException("가격 하락율이 가장 클 농산물을 조회할 수 없습니다");
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
        dto.setUnit(highestPriceDecreaseProduct.getProduct().getUnit());

        response.setData(dto);
        response.setMsg("good");
        response.setCode(200);
        highestRatioDecreadeProduct = response;
        return response;
    }


    @Override
    @Transactional(readOnly=true)
    public CommonDto findHighestRatioIncreaseProduct() throws EntityNotFoundException {

        if(highestRatioIncreaseProduct != null){
            return highestRatioIncreaseProduct;
        }

        CommonDto response = new CommonDto();
        ProductInfoDto dto = new ProductInfoDto();

        List<PricePredictEntity> pricePredictList = pricePredictRepository.findAllByDate(LocalDate.now().plusDays(1));
        if(pricePredictList.isEmpty()){
            throw new EntityNotFoundException("가격 상승율이 가장 클 농산물을 조회할 수 없습니다");
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
        dto.setUnit(highestPriceIncreaseProduct.getProduct().getUnit());

        response.setData(dto);
        response.setMsg("good");
        response.setCode(200);
        highestRatioIncreaseProduct = response;
        return response;
    }

    @Override
    @Transactional(readOnly=true)
    public CommonDto findPredictCard() throws EntityNotFoundException {

        if(predictCards != null){
            return predictCards;
        }

        CommonDto response = new CommonDto();

        List<PricePredictEntity> pricePredictList = pricePredictRepository.findAllByDate(LocalDate.now().plusDays(1));

        if(pricePredictList.isEmpty()){
            throw new EntityNotFoundException("농산물의 1일 뒤 예상 가격을 조회할 수 없습니다");
        }

        List<ProductInfoDto> predictList;
        predictList = pricePredictList.stream().filter(pricePredictEntity -> (
            pricePredictEntity.getGrade()==2
        )).map(entity-> new ProductInfoDto(entity.getProduct().getName(),
            entity.getPrice(),
            entity.getRatio(),
            entity.getProduct().getNickname(),
            entity.getProduct().getUnit())).collect(Collectors.toList());

        response.setData(predictList);
        response.setMsg("good");
        response.setCode(200);

        return response;
    }

    @Override
    @Transactional(readOnly=true)
    public CommonDto findPresentCard() throws EntityNotFoundException {

        CommonDto response = new CommonDto();

        List<PricePredictEntity> pricePresentList = pricePredictRepository.findAllByDate(LocalDate.now());

        if(pricePresentList.isEmpty()){
            throw new EntityNotFoundException("농산물의 오늘의 예상 가격을 조회할 수 없습니다");
        }

        List<ProductInfoDto> presentList;
        presentList = pricePresentList.stream().filter(pricePredictEntity -> (
            pricePredictEntity.getGrade()==2
        )).map(entity-> new ProductInfoDto(entity.getProduct().getName(),
            entity.getPrice(),
            entity.getRatio(),
            entity.getProduct().getNickname(),
            entity.getProduct().getUnit())).collect(Collectors.toList());

        response.setData(presentList);
        response.setMsg("good");
        response.setCode(200);

        return response;
    }

    @Override
    @Transactional(readOnly=true)
    public CommonDto findPastCard() throws EntityNotFoundException {
        CommonDto response = new CommonDto();

        LocalDate localDate = LocalDate.now().minusDays(7);
        Date date = java.sql.Date.valueOf(localDate);

        List<PriceHistoryEntity> priceHistoryList = priceHistoryRepository.findAllByDate(date);

        if(priceHistoryList.isEmpty()){
            throw new EntityNotFoundException("농산물의 7일 전 예상 가격을 조회할 수 없습니다");
        }

        List<ProductInfoDto> pastList;
        pastList = priceHistoryList.stream().filter(pricePredictEntity -> (
            pricePredictEntity.getGrade()==2
        )).map(entity-> new ProductInfoDto(entity.getProduct().getName(),
            entity.getPrice(),
            entity.getRatio(),
            entity.getProduct().getNickname(),
            entity.getProduct().getUnit())).collect(Collectors.toList());

        response.setData(pastList);
        response.setMsg("good");
        response.setCode(200);
        predictCards = response;
        return response;
    }

    @Override
    @Scheduled(cron = "0 0 4 * * *")
    public void resetData() {
        highestRatioDecreadeProduct = null;
        highestRatioIncreaseProduct = null;
        predictCards = null;
    }
}
