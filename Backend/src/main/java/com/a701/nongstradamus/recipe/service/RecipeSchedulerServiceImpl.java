package com.a701.nongstradamus.recipe.service;

import com.a701.nongstradamus.data.entity.PriceHistoryEntity;
import com.a701.nongstradamus.data.entity.RecipeEntity;
import com.a701.nongstradamus.data.repository.PriceHistoryRepository;
import com.a701.nongstradamus.data.repository.RecipeRepository;
import com.a701.nongstradamus.main.entity.PricePredictEntity;
import com.a701.nongstradamus.main.repository.PricePredictRepository;
import com.a701.nongstradamus.recipe.dto.RecipeRecommandDto;
import com.a701.nongstradamus.recipe.mapper.RecipeRecommandMapper;
import com.a701.nongstradamus.recipe.repository.RecipeRecommandRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class RecipeSchedulerServiceImpl implements RecipeSchedulerService{

    private final RecipeRepository recipeRepository;

    private final RecipeRecommandRepository recipeRecommandRepository;

    private final PricePredictRepository pricePredictRepository;

    private final PriceHistoryRepository priceHistoryRepository;

    @Override
    @Transactional
    @Scheduled(cron = "0 0 3 * * *")
    public void UpdateRecommandRecipe() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<PriceHistoryEntity> pricesYesterday = priceHistoryRepository.findAllByDate(
            java.sql.Timestamp.valueOf(yesterday.atStartOfDay()));
        List<PricePredictEntity> pricesToday = pricePredictRepository.findAllByDate(today);
        List<RecipeEntity> recipes = recipeRepository.findAll();
        Collections.sort(recipes, new Comparator<RecipeEntity>() {
            @Override
            public int compare(RecipeEntity o1, RecipeEntity o2) {
                double ratio1 = 0.0, ratio2 = 0.0;
                long priceToday1 = 0, priceYesterday1 = 0;
                long priceToday2 = 0, priceYesterday2 = 0L;
                String[] ingredients1 = o1.getIngredient().split(" ");
                String[] ingredients2 = o2.getIngredient().split(" ");
                for (PriceHistoryEntity priceYesterday : pricesYesterday) {
                    for (int i = 0; i < ingredients1.length; i++) {
                        if (priceYesterday.getProduct().equals(ingredients1[i])) {
                            priceYesterday1 += priceYesterday.getPrice();
                        }
                    }
                    for (int i = 0; i < ingredients2.length; i++) {
                        if (priceYesterday.getProduct().equals(ingredients2[i])) {
                            priceYesterday2 += priceYesterday.getPrice();
                        }
                    }
                }
                for (PricePredictEntity priceToday : pricesToday) {
                    for (int i = 0; i < ingredients1.length; i++) {
                        if (priceToday.getProduct().equals(ingredients1[i])) {
                            priceToday1 += priceToday.getPrice();
                        }
                    }
                    for (int i = 0; i < ingredients2.length; i++) {
                        if (priceToday.getProduct().equals(ingredients2[i])) {
                            priceToday2 += priceToday.getPrice();
                        }
                    }
                }
                ratio1 = (double) (priceToday1 - priceYesterday1) / priceYesterday1;
                ratio2 = (double) (priceToday2 - priceYesterday2) / priceYesterday2;
                double result = ratio1 - ratio2;
                if (result < 0.0) {
                    return -1;
                } else if (result == 0.0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        List<RecipeRecommandDto> dtos = new ArrayList<>();
        for (long i = 1; i <= 20; i++) {
            RecipeRecommandDto dto = new RecipeRecommandDto();
            dto.setId(i);
            dto.setRecipe(recipes.get((int) i));
            dtos.add(dto);
        }
        recipeRecommandRepository.saveAll(
            dtos.stream().map(RecipeRecommandMapper.INSANCE::fromDtoToEntity).collect(
                Collectors.toList()));
    }
}
