package com.siliconvalley.domain.canvas.dao;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasDto;
import com.siliconvalley.domain.canvas.dto.CanvasListSummary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface CanvasRepository extends JpaRepository<Canvas, Long> {

    @Query("SELECT new com.siliconvalley.domain.canvas.dto.CanvasDto(c.id, c.canvas) FROM Canvas c WHERE c.profileId = ?1")
    List<CanvasDto> findCanvasByProfileId(Long profileId);
    List<CanvasListSummary> findByProfileId(Long profileId);
    Optional<Canvas> findCanvasByIdAndProfileId(Long canvasId, Long profileId);
}