package com.siliconvalley.domain.image.controller;

import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/S3")
@RequiredArgsConstructor
public class S3UploadController {

    // 페이지 관리자용 사진 업로드

    private final S3ImageUploadService s3ImageUploadService;

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadFile(@RequestParam("file")MultipartFile file) throws IOException{
        Response response = Response.of(CommonCode.GOOD_REQUEST, s3ImageUploadService.uploadFile(file, "test"));
        return ResponseEntity.ok(response);
    }
}
