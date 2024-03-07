package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.WeatherEntity;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    // date 필드를 기반으로 검색
    List<WeatherEntity> findByDate(LocalDate date);
}
