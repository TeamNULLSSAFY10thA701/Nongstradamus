package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.PriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistoryEntity, Long> {

}
