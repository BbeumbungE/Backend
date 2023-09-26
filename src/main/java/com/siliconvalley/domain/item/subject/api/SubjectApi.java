package com.siliconvalley.domain.item.subject.api;

import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.domain.image.service.S3PathBuildService;
import com.siliconvalley.domain.item.subject.application.SketchCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectApi {

    private final S3ImageUploadService s3ImageUploadService;
    private final S3PathBuildService s3PathBuildService;
    private final SketchCreateService sketchCreateService;

    @PostMapping("/{subjectId}/sketches")
    public ResponseEntity addSketchToSubject(
            @PathVariable(name = "subjectId") Long subjectId,
            @RequestParam(name = "sketchImage") MultipartFile sketchImage
    ) throws IOException {
        String sketchImageUrl = s3ImageUploadService.uploadFile(sketchImage, s3PathBuildService.buildPathForItem("sketch"));
        return ResponseEntity.status(HttpStatus.CREATED).body(sketchCreateService.createSketch(subjectId, sketchImageUrl));
    }
}
