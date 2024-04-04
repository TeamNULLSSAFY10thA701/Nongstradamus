package com.a701.nongstradamus.recipe.entity;

import com.a701.nongstradamus.data.entity.RecipeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipeRecommand")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRecommandEntity {

    @Id
    @Column(name = "recipeRecommandId")
    private Long id;

    @OneToOne
    @JoinColumn(name = "recipeId")
    private RecipeEntity recipe;
}
