package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.WholesaleMarketCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WholesaleMarketCodeRepository extends JpaRepository<WholesaleMarketCodeEntity, Long> {

}
