package com.siliconvalley.domain.canvas.dao;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.exception.CanvasNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CanvasFindDao {

    private CanvasRepository canvasRepository;

    public Canvas findById(Long canvasId){
        Optional<Canvas> canvas = canvasRepository.findById(canvasId);
        canvas.orElseThrow(()-> new CanvasNotFoundException(canvasId + "번 그림" ));
        return canvas.get();
    }

}
