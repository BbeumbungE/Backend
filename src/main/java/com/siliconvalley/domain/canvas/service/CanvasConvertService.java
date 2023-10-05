package com.siliconvalley.domain.canvas.service;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasCreateDto;
import com.siliconvalley.domain.canvas.dto.ConvertEventDto;
import com.siliconvalley.domain.canvas.dto.DemoConversionDto;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.item.subject.dto.SubjectCachingDto;
import com.siliconvalley.domain.pix2pix.dao.Pix2PixFindDao;
import com.siliconvalley.domain.pix2pix.dto.UseModelDto;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.rabbitMQ.dto.DemoConversionResponse;
import com.siliconvalley.domain.rabbitMQ.dto.SketchConversionResponse;
import com.siliconvalley.domain.rabbitMQ.service.ConvertRequestSender;
import com.siliconvalley.domain.sse.application.CanvasSseEmitterFinder;
import com.siliconvalley.domain.sse.application.ConvertResultSender;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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
    private final CanvasSseEmitterFinder canvasSseEmitterFinder;
    private final ConvertResultSender convertResultSender;
    private final Pix2PixFindDao pix2PixFindDao;


    public Response convertSketchToCanvas(Long profileId, Long subjectId, String sketch){
        Profile profile = profileFindDao.findById(profileId);
        Subject subject = subjectFindDao.findById(subjectId);
        Canvas canvas = canvasCreateService.createCanvas(CanvasCreateDto.builder().subject(subject).sketchUrl(sketch).profile(profile).build());
        return convertRequestSender.sendSketchConversionRequest(sketch, canvas.getId(), profileId, subject);
    }

    public Response convertSketchToCanvasDemo(String sketch, String tempId, Long subjectId){
        UseModelDto dto = pix2PixFindDao.findBySubjectId(subjectId);
        return convertRequestSender.sendDemoConversionRequest(sketch, tempId, dto.getModelName());
    }

    public Response updateSketchAndCanvas(Long profileId, Long canvasId, String sketch){
        Canvas canvas = canvasFindDao.findById(canvasId);
        return canvasUpdateService.updateSketchAndCanvas(canvas, sketch, profileId);
    }

    public void updateConvertedData(SketchConversionResponse response){
        Canvas canvas = canvasFindDao.findById(response.getCanvasId());
        canvas.updateCanvas(response.getCanvasUrl());
        Long profileId = canvas.getProfile().getId();
        ConvertEventDto convertEventDto = new ConvertEventDto(canvas.getId(), response.getCanvasUrl());
        log.info(canvas.getId() + "번 그림에 대한 변환 처리 완료 및 SSE로 메시지 전송");
        convertResultSender.send(canvasSseEmitterFinder.findByProfileId(profileId), convertEventDto, profileId, "drawing");
    }
    public void sendConvertedDemoCanvas(DemoConversionResponse response){
        DemoConversionDto dto = new DemoConversionDto(response.getCanvasUrl());
        convertResultSender.send(canvasSseEmitterFinder.findByTempId(response.getTempId()), dto, response.getTempId(), "demo");
    }
}
