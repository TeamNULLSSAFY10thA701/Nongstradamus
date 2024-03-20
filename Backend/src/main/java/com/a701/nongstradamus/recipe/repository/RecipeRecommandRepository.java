package com.a701.nongstradamus.recipe.repository;

import com.a701.nongstradamus.recipe.entity.RecipeRecommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRecommandRepository extends JpaRepository<RecipeRecommandEntity, Long> {

}
