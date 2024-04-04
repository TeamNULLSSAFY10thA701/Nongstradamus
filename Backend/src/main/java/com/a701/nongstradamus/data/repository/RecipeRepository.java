package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.RecipeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    //recipe 테이블이 가지고 있는 모든 recipeTitle 조회
    @Query("SELECT r.title FROM RecipeEntity r")
    List<String> findAllTitles();
}
