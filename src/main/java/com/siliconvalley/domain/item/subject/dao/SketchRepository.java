package com.siliconvalley.domain.item.subject.dao;

import com.siliconvalley.domain.item.subject.domain.Sketch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SketchRepository extends JpaRepository<Sketch, Long> {

    List<Sketch> findBySubjectId(Long subjectId);
}
