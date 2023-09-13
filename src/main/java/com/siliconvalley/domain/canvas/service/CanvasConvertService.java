package com.siliconvalley.domain.canvas.service;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasCreateDto;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.rabbitMQ.dto.SketchConversionResponse;
import com.siliconvalley.domain.rabbitMQ.service.ConvertRequestSender;
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
        canvas.updateSketch(response.getCanvasUrl());
    }
}
