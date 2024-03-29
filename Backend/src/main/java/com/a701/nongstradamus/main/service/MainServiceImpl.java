package com.a701.nongstradamus.main.service;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.data.entity.PriceHistoryEntity;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.main.dto.CardDto;
import com.a701.nongstradamus.main.dto.ProductInfoDto;
import com.a701.nongstradamus.main.dto.ProductPriceDto;
import com.a701.nongstradamus.main.dto.RecommendDto;
import com.a701.nongstradamus.main.entity.PricePredictEntity;
import com.a701.nongstradamus.main.repository.CardRecommendRepository;
import com.a701.nongstradamus.main.repository.PricePredictRepository;
import com.a701.nongstradamus.main.repository.ProductRecommendRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    private final ProductRecommendRepository productRecommendRepository;

    private final CardRecommendRepository cardRecommendRepository;

    private CommonDto highestRatioDecreadeProduct;

    private CommonDto highestRatioIncreaseProduct;

    private CommonDto predictCards;

    private CommonDto todayRecommend;

    private CommonDto tomorrowRecommend;

    private CommonDto recommendCards;

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
        todayRecommend = null;
        tomorrowRecommend = null;
        recommendCards = null;
    }

    @Override
    public CommonDto findTodayRecommendData() {
        if(todayRecommend != null){
            return todayRecommend;
        }
        List<ProductPriceDto> productPrices = productRecommendRepository.findAll()
            .stream().map(entity ->{
                ProductPriceDto dto = new ProductPriceDto();
                dto.setName(entity.getName());
                dto.setUnit(entity.getUnit());
                dto.setNickname(entity.getNickname());
                dto.setPrices(new Long[] {
                    entity.getPriceHistory7(),
                    entity.getPricePredict6(),
                    entity.getPriceHistory5(),
                    entity.getPriceHistory4(),
                    entity.getPriceHistory3(),
                    entity.getPriceHistory2(),
                    entity.getPriceHistory1(),
                    entity.getPriceToday(),
                    entity.getPricePredict1(),
                    entity.getPricePredict2(),
                    entity.getPricePredict3(),
                    entity.getPricePredict4(),
                    entity.getPricePredict5(),
                    entity.getPricePredict6()
                });
                dto.setMention(true);
                return dto;
            }).collect(Collectors.toList());
        if(productPrices.isEmpty()){
            throw new EntityNotFoundException("404");
        }
        Collections.sort(productPrices, new Comparator<ProductPriceDto>() {
            @Override
            public int compare(ProductPriceDto o1, ProductPriceDto o2) {
                if(o1.getMention() == o2.getMention()){
                    double ratio1 = (o1.getPrices()[7] - o1.getPrices()[6])/(double)o1.getPrices()[6];
                    double ratio2 = (o2.getPrices()[7] - o2.getPrices()[6])/(double)o2.getPrices()[6];
                    return ratio1 == ratio2 ? 0 : ratio1 < ratio2 ? -1 : 1;
                }else{
                    return o1.getMention() - o2.getMention();
                }
            }
        });
        RecommendDto data = new RecommendDto();
        data.setName(productPrices.get(0).getName());
        data.setNickname(productPrices.get(0).getNickname());
        data.setUnit(productPrices.get(0).getUnit());
        data.setMention(productPrices.get(0).getMention());
        data.setPriceToday(productPrices.get(0).getPrices()[7]);
        data.setPriceTomorrow(productPrices.get(0).getPrices()[8]);
        todayRecommend = new CommonDto<RecommendDto>(data, "조회 성공", 200);
        return todayRecommend;
    }

    @Override
    public CommonDto findTomorrowRecommendData() {
        if(tomorrowRecommend != null){
            return tomorrowRecommend;
        }
        List<ProductPriceDto> productPrices = productRecommendRepository.findAll()
            .stream().map(entity ->{
                ProductPriceDto dto = new ProductPriceDto();
                dto.setName(entity.getName());
                dto.setUnit(entity.getUnit());
                dto.setNickname(entity.getNickname());
                dto.setPrices(new Long[] {
                    entity.getPriceHistory7(),
                    entity.getPricePredict6(),
                    entity.getPriceHistory5(),
                    entity.getPriceHistory4(),
                    entity.getPriceHistory3(),
                    entity.getPriceHistory2(),
                    entity.getPriceHistory1(),
                    entity.getPriceToday(),
                    entity.getPricePredict1(),
                    entity.getPricePredict2(),
                    entity.getPricePredict3(),
                    entity.getPricePredict4(),
                    entity.getPricePredict5(),
                    entity.getPricePredict6()
                });
                dto.setMention(false);
                return dto;
            }).collect(Collectors.toList());
        if(productPrices.isEmpty()){
            throw new EntityNotFoundException("404");
        }
        Collections.sort(productPrices, new Comparator<ProductPriceDto>() {
            @Override
            public int compare(ProductPriceDto o1, ProductPriceDto o2) {
                if(o1.getMention() == o2.getMention()){
                    double ratio1 = (o1.getPrices()[8] - o1.getPrices()[7])/(double)o1.getPrices()[7];
                    double ratio2 = (o2.getPrices()[8] - o2.getPrices()[7])/(double)o2.getPrices()[7];
                    return ratio1 == ratio2 ? 0 : ratio1 > ratio2 ? -1 : 1;
                }else{
                    return o1.getMention() - o2.getMention();
                }
            }
        });
        RecommendDto data = new RecommendDto();
        data.setName(productPrices.get(0).getName());
        data.setNickname(productPrices.get(0).getNickname());
        data.setUnit(productPrices.get(0).getUnit());
        data.setMention(productPrices.get(0).getMention());
        data.setPriceToday(productPrices.get(0).getPrices()[7]);
        data.setPriceTomorrow(productPrices.get(0).getPrices()[8]);
        tomorrowRecommend = new CommonDto<RecommendDto>(data, "조회 성공", 200);
        return tomorrowRecommend;
    }

    @Override
    public CommonDto listBestRecommendCards() {
        if(recommendCards!=null){
            return recommendCards;
        }
        List<CardDto> list = cardRecommendRepository.findAll().stream().map(entity->{
            Double ratio = (entity.getPriceToday() - entity.getPriceTomorrow()) / (double) entity.getPriceTomorrow();
            ratio = Math.round(ratio * 10000) / 100.0;
            return new CardDto(entity.getName(),entity.getNickname(), entity.getUnit(), entity.getPriceToday(), entity.getPriceTomorrow(), ratio, 0);
        }).collect(Collectors.toList());
        Collections.sort(list, new Comparator<CardDto>() {
            @Override
            public int compare(CardDto o1, CardDto o2) {
                return o1.getRatio() - o2.getRatio() == 0.0 ? 0 : o1.getRatio() - o2.getRatio() < 0.0 ? -1 : 1;
            }
        });
        List<CardDto> data = new ArrayList<>();
        for(int i=0; i<5; i++){
            list.get(i).setRank(i+1);
            data.add(list.get(i));
        }
        recommendCards = new CommonDto<List>(data, "조회 성공", 200);
        return recommendCards;
    }
}
