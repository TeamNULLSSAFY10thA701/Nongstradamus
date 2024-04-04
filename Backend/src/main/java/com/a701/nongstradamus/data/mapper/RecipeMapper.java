package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.RecipeDto;
import com.a701.nongstradamus.data.entity.RecipeEntity;
import java.util.Optional;
import org.mapstruct.Mapper;

@Mapper
public interface RecipeMapper {
    public static RecipeMapper INSTANCE = new RecipeMapperImpl();

    RecipeEntity fromDtoToEntity(RecipeDto recipeDto);

    RecipeDto fromEntityToDto(RecipeEntity recipeEntity);
}
