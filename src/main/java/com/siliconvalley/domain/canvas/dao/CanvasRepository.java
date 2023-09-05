package com.siliconvalley.domain.canvas.dao;

import com.siliconvalley.domain.canvas.domain.Canvas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanvasRepository extends JpaRepository<Canvas, Long> {
}
