package com.siliconvalley.domain.canvas.service;

import com.siliconvalley.domain.canvas.code.CanvasCode;
import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.dao.CanvasRepository;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.exception.CanvasIllegalDeleteException;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CanvasDeleteService {

    private final CanvasFindDao canvasFindDao;
    private final CanvasRepository canvasRepository;

    public Response deleteCanvas(Long profileId, Long canvasId){
        Canvas canvas = canvasFindDao.findById(canvasId);
        if (canvas.getProfile().getId() != profileId){
            throw new CanvasIllegalDeleteException("삭제 권한이 없는 유저입니다.");
        }
        canvasRepository.delete(canvas);
        return Response.of(CanvasCode.DELETE_CANVAS_SUCCESS);
    }
}
