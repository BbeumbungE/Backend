package com.siliconvalley.domain.canvas.dao;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasListResponse;
import com.siliconvalley.domain.canvas.dto.CanvasResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CanvasRepository extends JpaRepository<Canvas, Long> {
    Page<CanvasListResponse> findCanvasByProfileId(Long profileId, Pageable pageable);
    Optional<Canvas> findCanvasByIdAndProfileId(Long canvasId, Long profileId);
}
