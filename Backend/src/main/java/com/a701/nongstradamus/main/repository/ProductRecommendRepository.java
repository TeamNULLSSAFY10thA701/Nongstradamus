package com.a701.nongstradamus.main.repository;

import com.a701.nongstradamus.main.entity.ProductRecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRecommendRepository extends JpaRepository<ProductRecommendEntity, Long> {

}
