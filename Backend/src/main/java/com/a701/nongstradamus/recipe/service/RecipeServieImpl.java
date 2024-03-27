package com.a701.nongstradamus.recipe.service;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.data.dto.RecipeDto;
import com.a701.nongstradamus.data.entity.RecipeEntity;
import com.a701.nongstradamus.data.mapper.RecipeMapper;
import com.a701.nongstradamus.data.repository.RecipeRepository;
import com.a701.nongstradamus.recipe.dto.RecipeTitleDto;
import com.a701.nongstradamus.recipe.entity.RecipeRecommandEntity;
import com.a701.nongstradamus.recipe.entity.RecipeViewEntity;
import com.a701.nongstradamus.recipe.repository.RecipeRecommandRepository;
import com.a701.nongstradamus.recipe.repository.RecipeViewRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecipeServieImpl implements RecipeService{

    private final RecipeRecommandRepository recipeRecommandRepository;

    private final RecipeRepository recipeRepository;

    private final RecipeViewRepository recipeViewRepository;

    @Override
    @Transactional(readOnly = true)
    public CommonDto findRecipesList(int pageNumber) {

        Page<RecipeRecommandEntity> page = recipeRecommandRepository.findAll(
            PageRequest.of(pageNumber, 4));
        if (page.isEmpty()) {
            throw new EntityNotFoundException("404");
        }
        List<RecipeTitleDto> list = page.get().map(recipeRecommandEntity -> {
            return new RecipeTitleDto(recipeRecommandEntity.getRecipe().getTitle(),
                recipeRecommandEntity.getRecipe().getImage(),
                recipeRecommandEntity.getRecipe().getId());
        }).collect(Collectors.toList());
        return new CommonDto<List>(list, "조회 성공", 200);
    }

    @Override
    @Transactional(readOnly = true)
    public CommonDto findRecipe(Long id) {
        Optional<RecipeEntity> recipe = recipeRepository.findById(id);
        if(recipe.isEmpty()){
            throw new EntityNotFoundException("404");
        }
        return new CommonDto<RecipeDto>(RecipeMapper.INSTANCE.fromEntityToDto(recipe.get()), "조회 성공", 200);
    }

    @Override
    @Transactional(readOnly = true)
    public CommonDto findRecipesListBy(String sortBase, boolean sortMethod, int pageNumber) {
        Sort sort = sortMethod ? Sort.by(sortBase).ascending() : Sort.by(sortBase).descending();
        Page<RecipeViewEntity> page = recipeViewRepository.findAll(
            PageRequest.of(pageNumber, 4, sort));
        if (page.isEmpty()) {
            throw new EntityNotFoundException("404");
        }
        List<RecipeTitleDto> list = page.get().map(entity -> {
            return new RecipeTitleDto(entity.getTitle(),entity.getImage(), entity.getId());
        }).collect(Collectors.toList());
        return new CommonDto<List>(list, "조회 성공", 200);
    }

}