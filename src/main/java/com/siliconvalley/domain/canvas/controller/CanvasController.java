package com.siliconvalley.domain.canvas.controller;

import com.siliconvalley.domain.canvas.service.CanvasConvertService;
import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.domain.image.service.S3PathBuildService;
import com.siliconvalley.domain.sse.application.CanvasSseEmitterService;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/canvases")
@Slf4j
public class CanvasController {

    private final CanvasConvertService canvasConvertService;
    private final S3ImageUploadService s3ImageUploadService;
    private final S3PathBuildService s3PathBuildService;

    @PostMapping("")
    public ResponseEntity<Response> convertSketchToCanvas(
            @RequestParam("sketchFile") MultipartFile sketchFile,
            @RequestParam Long profileId,
            @RequestParam Long subjectId
    ) throws IOException {
        log.info(profileId + "번 프로필의 " + subjectId + "번 주제에 대한 최초 변환 요청");
        String sketch = s3ImageUploadService.uploadFile(sketchFile, s3PathBuildService.buildPath(profileId, "sketch"));
        Response response = canvasConvertService.convertSketchToCanvas(profileId, subjectId, sketch);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{canvasId}")
    public ResponseEntity<Response> updateSketchAndCanvas(
            @RequestParam ("file") MultipartFile sketchFile,
            @RequestParam Long profileId,
            @PathVariable Long canvasId
    ) throws IOException {
        log.info(profileId + "번 프로필의 " + canvasId + "번 그림에 대한 변환 요청");
        String sketch = s3ImageUploadService.uploadFile(sketchFile, s3PathBuildService.buildPath(profileId, "sketch"));
        Response response = canvasConvertService.updateSketchAndCanvas(profileId, canvasId, sketch);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/demo/subject/{subjectId}/tempId/{tempId}")
    public ResponseEntity<Response> convertSketchToCanvasTest(
            @RequestParam ("sketchFile") MultipartFile sketchFile,
            @PathVariable String tempId,
            @PathVariable Long subjectId
    )throws IOException{
        String sketchUrl = s3ImageUploadService.uploadFile(sketchFile, "demo");
        Response response = canvasConvertService.convertSketchToCanvasDemo(sketchUrl, tempId, subjectId);
        return ResponseEntity.ok(response);
    }
}
