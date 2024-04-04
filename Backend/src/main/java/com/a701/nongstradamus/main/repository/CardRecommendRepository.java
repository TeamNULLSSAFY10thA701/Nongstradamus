package com.a701.nongstradamus.main.repository;

import com.a701.nongstradamus.main.entity.CardRecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRecommendRepository extends JpaRepository<CardRecommendEntity, Long> {

}
