package com.a701.nongstradamus.data.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name =  "recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeEntity {

    @Id
    @Column(name = "recipeId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipeTitle",nullable = false)
    private String title;

    @Column(name = "recipeIngredient",nullable = false)
    private String ingredient;

    //요리 대표 사진의 이미지 주소가 들어감
    @Column(name = "recipeImage")
    private String image;

    @Column(name = "recipeContent")
    private String content;
}
