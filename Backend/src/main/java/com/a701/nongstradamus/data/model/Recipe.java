package com.a701.nongstradamus.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter

    private Long recipeId;

    private String recipeTitle;
    private String recipeIngredient;
    private String recipeImage;
    private String recipeContent;

    public Recipe() {
        // 기본 생성자
    }

    public Recipe(String recipeTitle, String recipeIngredient, String recipeImage, String recipeContent) {
        this.recipeTitle = recipeTitle;
        this.recipeIngredient = recipeIngredient;
        this.recipeImage = recipeImage;
        this.recipeContent = recipeContent;
    }

}
