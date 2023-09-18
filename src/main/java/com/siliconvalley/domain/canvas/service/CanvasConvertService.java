package com.siliconvalley.domain.canvas.service;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasCreateDto;
import com.siliconvalley.domain.canvas.dto.ConvertEventDto;
import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.domain.image.service.S3PathBuildService;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.rabbitMQ.dto.SketchConversionResponse;
import com.siliconvalley.domain.rabbitMQ.service.ConvertRequestSender;
import com.siliconvalley.domain.sse.application.SseEmitterFinder;
import com.siliconvalley.domain.sse.application.SseEmitterSender;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CanvasConvertService {

    private final ProfileFindDao profileFindDao;
    private final SubjectFindDao subjectFindDao;
    private final CanvasFindDao canvasFindDao;
    private final ConvertRequestSender convertRequestSender;
    private final CanvasUpdateService canvasUpdateService;
    private final CanvasCreateService canvasCreateService;
    private final S3ImageUploadService s3ImageUploadService;
    private final S3PathBuildService s3PathBuildService;
    private final SseEmitterSender sseEmitterSender;
    private final SseEmitterFinder sseEmitterFinder;

    public Response convertSketchToCanvas(Long profileId, Long subjectId, String sketch){
        Profile profile = profileFindDao.findById(profileId);
        Subject subject = subjectFindDao.findById(subjectId);
        Canvas canvas = canvasCreateService.createCanvas(CanvasCreateDto.builder().subject(subject).sketchUrl(sketch).profile(profile).build());
        return convertRequestSender.sendSketchConversionRequest(sketch, canvas.getId(), profileId, subjectId);
    }

    public Response updateSketchAndCanvas(Long profileId, Long canvasId, String sketch){
        Canvas canvas = canvasFindDao.findById(canvasId);
        return canvasUpdateService.updateSketchAndCanvas(canvas, sketch, profileId);
    }

      public void updateConvertedData(SketchConversionResponse response){
        Canvas canvas = canvasFindDao.findById(response.getCanvasId());
        Long profileId = canvas.getProfile().getId();
        String id = profileId + "_" + System.currentTimeMillis();
        canvas.updateCanvas(response.getCanvasUrl());
        sseEmitterSender.send(sseEmitterFinder.findByProfileId(profileId), id, new ConvertEventDto(canvas.getId(), response.getCanvasUrl()), profileId);
    }
}
