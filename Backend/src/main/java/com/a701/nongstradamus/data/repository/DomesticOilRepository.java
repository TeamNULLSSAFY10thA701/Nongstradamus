package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.DomesticOilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DomesticOilRepository extends JpaRepository<DomesticOilEntity, Long> {
    Optional<DomesticOilEntity> findByDate(LocalDate date);
    boolean existsByDate(LocalDate date);
}
