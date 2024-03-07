package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.GlobalOilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface GlobalOilRepository extends JpaRepository<GlobalOilEntity, Long> {
    Optional<GlobalOilEntity> findByDate(LocalDate date);
    boolean existsByDate(LocalDate date);
}


