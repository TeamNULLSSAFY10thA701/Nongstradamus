package com.a701.nongstradamus.price.service;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.data.repository.ProductRepository;
import com.a701.nongstradamus.main.entity.PricePredictEntity;
import com.a701.nongstradamus.main.repository.PricePredictRepository;
import com.a701.nongstradamus.price.dto.PriceGraphDto;
import com.a701.nongstradamus.price.dto.PriceTableDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AllServiceImpl implements AllService{

    private final ProductRepository productRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    private final PricePredictRepository pricePredictRepository;

    @Override
    @Transactional(readOnly = true)
    public CommonDto findProducts(Integer category) {
        Map<String, Object> data = new HashMap<>();
        List<ProductEntity> products = productRepository.findAllByCategory(category);
        List<PriceTableDto> tableDtos = new ArrayList<>();
        for (ProductEntity product : products) {
            PriceTableDto tableDto = new PriceTableDto();
            tableDto.setName(product.getName());
            tableDto.setNickname(product.getNickname());
            tableDto.setUnit(product.getNickname());
            Map<String, Map> grade = new HashMap<>();
            grade.put("low", new HashMap<Integer, PriceGraphDto>());
            grade.put("mid", new HashMap<Integer, PriceGraphDto>());
            grade.put("good", new HashMap<Integer, PriceGraphDto>());
            grade.put("best", new HashMap<Integer, PriceGraphDto>());
            List<PriceGraphDto> lowList, midList, goodList, bestList;
            LocalDate today = LocalDate.now();
            LocalDate targetDay;
            targetDay = today.minusDays(7);
            lowList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 1).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            midList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 2).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            goodList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 3).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            bestList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 4).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            grade.get("low").put(-1, lowList.isEmpty() ? new PriceGraphDto() : lowList.get(0));
            grade.get("mid").put(-1, midList.isEmpty() ? new PriceGraphDto() : midList.get(0));
            grade.get("good").put(-1, goodList.isEmpty() ? new PriceGraphDto() : goodList.get(0));
            grade.get("best").put(-1, bestList.isEmpty() ? new PriceGraphDto() : bestList.get(0));
            targetDay = today.minusDays(14);
            lowList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 1).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            midList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 2).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            goodList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 3).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            bestList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 4).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            grade.get("low").put(-2, lowList.isEmpty() ? new PriceGraphDto() : lowList.get(0));
            grade.get("mid").put(-2, midList.isEmpty() ? new PriceGraphDto() : midList.get(0));
            grade.get("good").put(-2, goodList.isEmpty() ? new PriceGraphDto() : goodList.get(0));
            grade.get("best").put(-2, bestList.isEmpty() ? new PriceGraphDto() : bestList.get(0));
            targetDay = today.minusDays(21);
            lowList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 1).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            midList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 2).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            goodList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 3).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            bestList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 4).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            grade.get("low").put(-3, lowList.isEmpty() ? new PriceGraphDto() : lowList.get(0));
            grade.get("mid").put(-3, midList.isEmpty() ? new PriceGraphDto() : midList.get(0));
            grade.get("good").put(-3, goodList.isEmpty() ? new PriceGraphDto() : goodList.get(0));
            grade.get("best").put(-3, bestList.isEmpty() ? new PriceGraphDto() : bestList.get(0));
            targetDay = today.minusDays(28);
            lowList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 1).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            midList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 2).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            goodList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 3).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            bestList = priceHistoryRepository.findAllByProductAndDateAndGrade(
                    product, java.sql.Timestamp.valueOf(targetDay.atStartOfDay()), 4).stream()
                .map(entity -> {
                    return new PriceGraphDto(entity.getPrice(), entity.getRatio());
                }).collect(
                    Collectors.toList());
            grade.get("low").put(-4, lowList.isEmpty() ? new PriceGraphDto() : lowList.get(0));
            grade.get("mid").put(-4, midList.isEmpty() ? new PriceGraphDto() : midList.get(0));
            grade.get("good").put(-4, goodList.isEmpty() ? new PriceGraphDto() : goodList.get(0));
            grade.get("best").put(-4, bestList.isEmpty() ? new PriceGraphDto() : bestList.get(0));
            targetDay = today;
            lowList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 1
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            midList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 2
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            goodList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            bestList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            grade.get("low").put(0, lowList.isEmpty() ? new PriceGraphDto() : lowList.get(0));
            grade.get("mid").put(0, midList.isEmpty() ? new PriceGraphDto() : midList.get(0));
            grade.get("good").put(0, goodList.isEmpty() ? new PriceGraphDto() : goodList.get(0));
            grade.get("best").put(0, bestList.isEmpty() ? new PriceGraphDto() : bestList.get(0));
            tableDto.setPresent(midList.isEmpty() ? null : midList.get(0).getPrice());
            targetDay = today.plusDays(7);
            lowList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 1
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            midList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 2
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            goodList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            bestList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            grade.get("low").put(1, lowList.isEmpty() ? new PriceGraphDto() : lowList.get(0));
            grade.get("mid").put(1, midList.isEmpty() ? new PriceGraphDto() : midList.get(0));
            grade.get("good").put(1, goodList.isEmpty() ? new PriceGraphDto() : goodList.get(0));
            grade.get("best").put(1, bestList.isEmpty() ? new PriceGraphDto() : bestList.get(0));
            targetDay = today.plusDays(14);
            lowList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 1
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            midList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 2
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            goodList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            bestList = pricePredictRepository.findAllByProductAndDateAndGrade(
                product, targetDay, 3
            ).stream().map(entity -> {
                return new PriceGraphDto(entity.getPrice(), entity.getRatio());
            }).collect(Collectors.toList());
            grade.get("low").put(2, lowList.isEmpty() ? new PriceGraphDto() : lowList.get(0));
            grade.get("mid").put(2, midList.isEmpty() ? new PriceGraphDto() : midList.get(0));
            grade.get("good").put(2, goodList.isEmpty() ? new PriceGraphDto() : goodList.get(0));
            grade.get("best").put(2, bestList.isEmpty() ? new PriceGraphDto() : bestList.get(0));
            data.put(product.getNickname(), grade);
            targetDay = today.plusDays(1);
            List<PricePredictEntity> future = pricePredictRepository.findAllByProductAndDateAndGrade(product, targetDay, 2);
            tableDto.setFuture(future.isEmpty() ? null : future.get(0).getPrice());
            tableDto.setRatio();
            tableDtos.add(tableDto);
        }
        data.put("table", tableDtos);
        return new CommonDto<Map>(data, "조회 성공", 200);
    }
}
