package com.a701.nongstradamus.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    private String title;
    private String ingredient;
    private String image;
    private String content;
    private Double energy;
    private Double carbohydrate;
    private Double protein;
    private Double fat;
    private Double natrium;
}
