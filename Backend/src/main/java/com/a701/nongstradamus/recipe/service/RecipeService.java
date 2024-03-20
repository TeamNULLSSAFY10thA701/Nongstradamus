package com.a701.nongstradamus.recipe.service;

import com.a701.nongstradamus.common.CommonDto;

public interface RecipeService {

    CommonDto findRecipesList(int pageNumber);

    CommonDto findRecipe(Long id);

    CommonDto findRecipesListBy(String sortBase, boolean sortMethod, int pageNumber);

}
