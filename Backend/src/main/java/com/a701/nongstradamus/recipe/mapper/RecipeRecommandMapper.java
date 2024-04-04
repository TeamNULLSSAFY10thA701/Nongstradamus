package com.a701.nongstradamus.recipe.mapper;

import com.a701.nongstradamus.recipe.dto.RecipeRecommandDto;
import com.a701.nongstradamus.recipe.entity.RecipeRecommandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeRecommandMapper {

    public static RecipeRecommandMapper INSANCE = Mappers.getMapper(RecipeRecommandMapper.class);
    RecipeRecommandEntity fromDtoToEntity(RecipeRecommandDto recipeRecommandDto);
}
