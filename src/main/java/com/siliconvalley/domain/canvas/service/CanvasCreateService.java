package com.siliconvalley.domain.canvas.service;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasCreateDto;
import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CanvasCreateService {

    private final S3ImageUploadService s3ImageUploadService;

    public Canvas createCanvas(CanvasCreateDto dto){
        Profile
        return dto.toEntity();
    }

}
