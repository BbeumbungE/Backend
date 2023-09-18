package com.siliconvalley.domain.canvas.controller;

import com.siliconvalley.domain.canvas.service.CanvasConvertService;
import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.domain.image.service.S3PathBuildService;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/canvases")
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
        String sketch = s3ImageUploadService.uploadFile(sketchFile, s3PathBuildService.buildPath(profileId, "sketch"));
        Response response = canvasConvertService.updateSketchAndCanvas(profileId, canvasId, sketch);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

}
