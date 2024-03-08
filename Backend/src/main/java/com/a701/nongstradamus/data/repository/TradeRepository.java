package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.TradeEntity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

    List<TradeEntity> findAllByProductIdAndDate(Long productId, Date date);

}
