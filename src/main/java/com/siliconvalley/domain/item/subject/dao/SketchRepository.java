package com.siliconvalley.domain.item.subject.dao;

import com.siliconvalley.domain.item.subject.domain.Sketch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SketchRepository extends JpaRepository<Sketch, Long> {
    List<Sketch> findBySubject_Id(Long subjectId);
}
