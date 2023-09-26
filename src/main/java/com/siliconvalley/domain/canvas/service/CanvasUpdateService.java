package com.siliconvalley.domain.canvas.service;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasConvertResponse;
import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.domain.post.service.RankCachingService;
import com.siliconvalley.domain.rabbitMQ.code.RabbitMQCode;
import com.siliconvalley.domain.rabbitMQ.service.ConvertRequestSender;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CanvasUpdateService {

    private final S3ImageUploadService s3ImageUploadService;
    private final ConvertRequestSender convertRequestSender;
    private final RankCachingService rankCachingService;

    public Response updateSketchAndCanvas(Canvas canvas, String newSketch, Long profileId){
        s3ImageUploadService.deleteImage(canvas.getCanvas());
        s3ImageUploadService.deleteImage(canvas.getSketch());
        canvas.updateSketch(newSketch);
        convertRequestSender.sendSketchConversionRequest(newSketch, canvas.getId(), profileId, canvas.getSubject());
        return Response.of(RabbitMQCode.CONVERSION_RESPONSE_SUCCESS, new CanvasConvertResponse(canvas.getId(), rankCachingService.getTopPostThisWeek(canvas.getSubject().getId())));
    }
}
