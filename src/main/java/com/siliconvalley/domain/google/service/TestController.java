package com.siliconvalley.domain.google.service;

import com.siliconvalley.domain.stage.domain.Score;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/google")
@RequiredArgsConstructor
public class TestController {

    private final VisionDetectingService visionDetectingService;

    @GetMapping("/{canvasId}")
    public ResponseEntity<Response> test(@PathVariable Long canvasId){
        Score score = visionDetectingService.calculateCanvasScore(canvasId);
        return ResponseEntity.ok(Response.of(CommonCode.GOOD_REQUEST, score.getScoreValue()));

    }

}
