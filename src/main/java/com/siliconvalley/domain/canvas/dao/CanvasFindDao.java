package com.siliconvalley.domain.canvas.dao;

import com.siliconvalley.domain.canvas.code.CanvasCode;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasListResponse;
import com.siliconvalley.domain.canvas.dto.CanvasResponse;
import com.siliconvalley.domain.canvas.exception.CanvasNotFoundException;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CanvasFindDao {

    private final CanvasRepository canvasRepository;

    public Canvas findById(Long canvasId){
        Optional<Canvas> canvas = canvasRepository.findById(canvasId);
        canvas.orElseThrow(()-> new CanvasNotFoundException(canvasId + "번 그림" ));
        return canvas.get();
    }

    public Response findByProfileId(Long profileId, Pageable pageable){
        Page<CanvasListResponse> canvases = canvasRepository.findCanvasByProfileId(profileId, pageable);
        return Response.of(CanvasCode.GET_CANVAS_SUCCESS, canvases);
    }

    public Response findCanvasDetail(Long profileId, Long canvasId){
        Optional<Canvas> canvas = canvasRepository.findCanvasByIdAndProfileId(canvasId, profileId);
        canvas.orElseThrow(() -> new CanvasNotFoundException(canvasId + "번 캔버스"));
        return Response.of(CanvasCode.GET_CANVAS_SUCCESS, new CanvasResponse(canvas.get()));
    }

}
