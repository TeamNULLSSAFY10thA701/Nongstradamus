package com.a701.nongstradamus.main.repository;

import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.main.entity.PricePredictEntity;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricePredictRepository extends JpaRepository<PricePredictEntity, Long> {
    List<PricePredictEntity> findAllByDate(LocalDate date);

    List<PricePredictEntity> findAllByProductAndDateAndGrade(ProductEntity product, LocalDate date, Integer garde);

}
