package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.WeatherOriginCodeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherOriginCodeRepository extends JpaRepository<WeatherOriginCodeEntity, String> {
    List<WeatherOriginCodeEntity> findAll();
}
