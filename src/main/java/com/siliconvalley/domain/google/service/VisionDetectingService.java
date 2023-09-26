package com.siliconvalley.domain.google.service;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.stage.domain.Score;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VisionDetectingService {

    private final GoogleVisionApiService visionService;
    private final CanvasFindDao canvasFindDao;

    public Score calculateCanvasScore(Long canvasId) {
        Canvas canvas = canvasFindDao.findById(canvasId);
        Map<String, Float> detectResult = visionService.detectObjects(canvas.getCanvas());
        Float detectionScore = detectResult.get(canvas.getSubject().getPix2Pix().getVisionName());

        return Score.determineScore(detectionScore);
    }
}
