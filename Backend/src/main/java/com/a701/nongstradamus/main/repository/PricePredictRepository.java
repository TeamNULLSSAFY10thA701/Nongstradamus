package com.a701.nongstradamus.main.repository;

import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.main.entity.PricePredictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricePredictRepository extends JpaRepository<PricePredictEntity, Long> {

}
