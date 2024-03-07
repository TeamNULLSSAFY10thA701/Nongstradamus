package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.RecipeDto;
import com.a701.nongstradamus.data.entity.RecipeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {
    public static RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeEntity fromDtoToEntity(RecipeDto recipeDto);
}
