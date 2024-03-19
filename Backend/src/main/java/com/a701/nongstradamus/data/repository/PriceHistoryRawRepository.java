package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.PriceHistoryRawEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceHistoryRawRepository extends JpaRepository<PriceHistoryRawEntity, Long> {

    List<PriceHistoryRawEntity> findAllByNameLikeAndIsSavedFalse(String name);
}
