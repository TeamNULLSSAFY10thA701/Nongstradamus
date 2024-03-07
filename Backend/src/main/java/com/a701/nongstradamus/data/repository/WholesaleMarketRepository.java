package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.entity.WholesaleMarketEntity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WholesaleMarketRepository extends JpaRepository<WholesaleMarketEntity, Long> {

    List<WholesaleMarketEntity> findAllByProductAndDate(ProductEntity product, Date date);
}
