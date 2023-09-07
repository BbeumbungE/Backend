package com.siliconvalley.domain.item.stage.dao;

import com.siliconvalley.domain.item.stage.domain.Stage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StageRepository extends JpaRepository<Stage, Long> {
    Page<Stage> findAll(Pageable pageable);
}
