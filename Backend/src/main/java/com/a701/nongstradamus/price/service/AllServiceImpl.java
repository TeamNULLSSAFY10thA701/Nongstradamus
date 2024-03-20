package com.a701.nongstradamus.price.service;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.data.repository.ProductRepository;
import com.a701.nongstradamus.main.repository.PricePredictRepository;
import com.a701.nongstradamus.price.dto.PriceDetailDto;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllServiceImpl implements AllService{

    private final ProductRepository productRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    private final PricePredictRepository pricePredictRepository;

    @Override
    public CommonDto findProducts(Integer category) {
        Map<String, Map> data = new HashMap<>();
        List<ProductEntity> products = productRepository.findAllByCategory(category);
        for (ProductEntity product : products) {
            Map<String, Map> grade = new HashMap<>();
            grade.put("low", new HashMap<Integer, PriceDetailDto>());
            grade.put("mid", new HashMap<Integer, PriceDetailDto>());
            grade.put("good", new HashMap<Integer, PriceDetailDto>());
            grade.put("best", new HashMap<Integer, PriceDetailDto>());
            List<PriceDetailDto> lowList, midList, goodList, bestList;
            LocalDate today = LocalDate.now();
            LocalDate targetDay;
            targetDay = today.minusDays(7);
            lowList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 1).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            midList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 2).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            goodList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 3).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            bestList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 4).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            grade.get("low").put(-1, lowList.isEmpty() ? null : lowList.get(0));
            grade.get("mid").put(-1, midList.isEmpty() ? null : midList.get(0));
            grade.get("good").put(-1, goodList.isEmpty() ? null : goodList.get(0));
            grade.get("best").put(-1, bestList.isEmpty() ? null : bestList.get(0));
            targetDay = today.minusDays(14);
            lowList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 1).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            midList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 2).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            goodList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 3).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            bestList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 4).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            grade.get("low").put(-2, lowList.isEmpty() ? null : lowList.get(0));
            grade.get("mid").put(-2, midList.isEmpty() ? null : midList.get(0));
            grade.get("good").put(-2, goodList.isEmpty() ? null : goodList.get(0));
            grade.get("best").put(-2, bestList.isEmpty() ? null : bestList.get(0));
            targetDay = today.minusDays(21);
            lowList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 1).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            midList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 2).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            goodList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 3).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            bestList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 4).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            grade.get("low").put(-3, lowList.isEmpty() ? null : lowList.get(0));
            grade.get("mid").put(-3, midList.isEmpty() ? null : midList.get(0));
            grade.get("good").put(-3, goodList.isEmpty() ? null : goodList.get(0));
            grade.get("best").put(-3, bestList.isEmpty() ? null : bestList.get(0));
            targetDay = today.minusDays(28);
            lowList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 1).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            midList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 2).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            goodList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 3).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            bestList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 4).stream()
                .map(entity -> {
                    return new PriceDetailDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            grade.get("low").put(-4, lowList.isEmpty() ? null : lowList.get(0));
            grade.get("mid").put(-4, midList.isEmpty() ? null : midList.get(0));
            grade.get("good").put(-4, goodList.isEmpty() ? null : goodList.get(0));
            grade.get("best").put(-4, bestList.isEmpty() ? null : bestList.get(0));
            targetDay = today;
            lowList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 1
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            midList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 2
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            goodList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            bestList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            grade.get("low").put(0, lowList.isEmpty() ? null : lowList.get(0));
            grade.get("mid").put(0, midList.isEmpty() ? null : midList.get(0));
            grade.get("good").put(0, goodList.isEmpty() ? null : goodList.get(0));
            grade.get("best").put(0, bestList.isEmpty() ? null : bestList.get(0));
            targetDay = today.plusDays(7);
            lowList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 1
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            midList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 2
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            goodList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            bestList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            grade.get("low").put(1, lowList.isEmpty() ? null : lowList.get(0));
            grade.get("mid").put(1, midList.isEmpty() ? null : midList.get(0));
            grade.get("good").put(1, goodList.isEmpty() ? null : goodList.get(0));
            grade.get("best").put(1, bestList.isEmpty() ? null : bestList.get(0));
            targetDay = today.plusDays(14);
            lowList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 1
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            midList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 2
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            goodList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            bestList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceDetailDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            grade.get("low").put(2, lowList.isEmpty() ? null : lowList.get(0));
            grade.get("mid").put(2, midList.isEmpty() ? null : midList.get(0));
            grade.get("good").put(2, goodList.isEmpty() ? null : goodList.get(0));
            grade.get("best").put(2, bestList.isEmpty() ? null : bestList.get(0));
            data.put(product.getNickname(), grade);
        }
        return new CommonDto<Map>(data, "조회 성공", 200);
    }
}
