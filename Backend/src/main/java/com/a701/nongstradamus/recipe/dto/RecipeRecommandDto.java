package com.a701.nongstradamus.recipe.dto;

import com.a701.nongstradamus.data.entity.RecipeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRecommandDto {

    private Long id;

    private RecipeEntity recipe;
}
