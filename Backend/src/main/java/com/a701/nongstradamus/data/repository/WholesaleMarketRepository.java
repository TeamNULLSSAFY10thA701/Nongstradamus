package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.OriginEntity;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.entity.WholesaleMarketEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WholesaleMarketRepository extends JpaRepository<WholesaleMarketEntity, Long> {

    List<WholesaleMarketEntity> findAllByProductAndDate(ProductEntity product, Date date);

    @Query("select wm.origin from WholesaleMarketEntity wm where wm.product=:product and wm.date=:date and wm.grade=:grade")
    List<OriginEntity> findOriginIdByProductAndDateAndGrade(@Param("product") ProductEntity product,@Param("date") Date date, @Param("garde") int grade);

    List<WholesaleMarketEntity> findAllByProductAndDateAndGardeAndOrigin(ProductEntity product, Date date, int grade, OriginEntity origin);
}
