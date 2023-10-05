package com.siliconvalley.domain.google.controller;

import com.siliconvalley.domain.google.service.VisionDetectingService;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/google")
@RequiredArgsConstructor
public class DetectingTestController {

    private final VisionDetectingService visionDetectingService;

    @GetMapping("/{canvasId}")
    public ResponseEntity<Response> test(
            @PathVariable Long canvasId
            ){
        return ResponseEntity.ok(visionDetectingService.testLabelDetecting(canvasId));
    }

    @GetMapping("/{canvasId}/score")
    public ResponseEntity<Response> scoreTest(
            @PathVariable Long canvasId
    ){
        return ResponseEntity.ok(Response.of(CommonCode.GOOD_REQUEST, visionDetectingService.calculateCanvasScore(canvasId).getScoreValue()));
    }

    @GetMapping("/score")
    public ResponseEntity<Response> scoreTest2(
            @RequestParam String filePath,
            @RequestParam String visionName
    ){
        return ResponseEntity.ok(Response.of(CommonCode.GOOD_REQUEST, visionDetectingService.calculateCanvasScore(filePath, visionName).getScoreValue()));
    }

}
