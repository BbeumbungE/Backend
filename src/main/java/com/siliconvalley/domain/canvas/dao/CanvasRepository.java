package com.siliconvalley.domain.canvas.dao;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasListSummary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CanvasRepository extends JpaRepository<Canvas, Long> {

    List<CanvasListSummary> findByProfileId(Long profileId);
    Optional<Canvas> findCanvasByIdAndProfileId(Long canvasId, Long profileId);
}