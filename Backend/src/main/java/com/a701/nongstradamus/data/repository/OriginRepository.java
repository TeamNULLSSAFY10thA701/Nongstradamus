package com.a701.nongstradamus.data.repository;

import com.a701.nongstradamus.data.entity.OriginEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<OriginEntity, Long> {

    List<OriginEntity> findAllByName(String name);
}
