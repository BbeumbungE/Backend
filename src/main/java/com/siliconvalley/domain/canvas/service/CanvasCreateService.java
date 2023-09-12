package com.siliconvalley.domain.canvas.service;

import com.siliconvalley.domain.canvas.dao.CanvasRepository;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CanvasCreateService {

    private final CanvasRepository canvasRepository;

    public Canvas createCanvas(CanvasCreateDto dto){
        Canvas canvas = dto.toEntity();
        return canvasRepository.save(canvas);
    }

}
