package com.a701.nongstradamus.recipe.service;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.data.repository.RecipeRepository;
import com.a701.nongstradamus.recipe.dto.RecipeTitleDto;
import com.a701.nongstradamus.recipe.entity.RecipeRecommandEntity;
import com.a701.nongstradamus.recipe.repository.RecipeRecommandRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecipeServieImpl implements RecipeService{

    private final RecipeRecommandRepository recipeRecommandRepository;

    private final RecipeRepository recipeRepository;

    @Override
    @Transactional(readOnly = true)
    public CommonDto findRecipesList(int pageNumber) {

        Page<RecipeRecommandEntity> page = recipeRecommandRepository.findAll(PageRequest.of(pageNumber, 5));
        if(page.getSize()==0){
            throw new EntityNotFoundException("404");
        }
        List<RecipeTitleDto> list = page.get().map(recipeRecommandEntity -> {
            return new RecipeTitleDto(recipeRecommandEntity.getRecipe().getTitle(), recipeRecommandEntity.getRecipe().getImage());
        }).collect(Collectors.toList());
        return new CommonDto<List>(list, "조회 성공", 200);
    }
}
