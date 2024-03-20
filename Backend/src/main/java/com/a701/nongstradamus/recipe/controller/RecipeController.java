package com.a701.nongstradamus.recipe.controller;

import com.a701.nongstradamus.common.CommonDto;
import com.a701.nongstradamus.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/{page-number}")
    public ResponseEntity<CommonDto> recipeList(@PathVariable("page-number")int pageNumber) {
        return new ResponseEntity<CommonDto>(recipeService.findRecipesList(pageNumber),
            HttpStatus.OK);
    }

    @GetMapping("/detail/{recipe-id}")
    public ResponseEntity<CommonDto> recipeDetail(@PathVariable("recipe-id") Long id){
        return new ResponseEntity<CommonDto>(recipeService.findRecipe(id), HttpStatus.OK);
    }
}
