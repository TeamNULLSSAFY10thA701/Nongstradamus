package com.a701.nongstradamus.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipeRecommandRecipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeViewEntity {

    @Id
    @Column(name = "recipeRecommandId")
    private Long recommandId;

    @Column(name = "recipeId")
    private Long id;

    @Column(name = "carbohydrate")
    private Double carbohydrate;

    @Column(name = "energy")
    private Double energy;

    @Column(name = "fat")
    private Double fat;

    @Column(name = "natrium")
    private Double natrium;

    @Column(name = "protein")
    private Double protain;

    @Column(name = "recipeContent")
    private String content;

    @Column(name = "recipeImage")
    private String image;

    @Column(name = "recipeIngredient")
    private String ingredient;

    @Column(name = "recipeTitle")
    private String title;
}
