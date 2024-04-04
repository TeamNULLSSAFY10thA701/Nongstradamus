package com.a701.nongstradamus.recipe.repository;

import com.a701.nongstradamus.recipe.entity.RecipeViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeViewRepository extends JpaRepository<RecipeViewEntity, Long> {

}
