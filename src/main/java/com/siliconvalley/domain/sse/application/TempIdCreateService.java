package com.siliconvalley.domain.sse.application;

import com.siliconvalley.domain.item.subject.dao.SketchFindDao;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Sketch;
import com.siliconvalley.domain.sse.code.SseCode;
import com.siliconvalley.domain.sse.dto.CreateTempIdResponse;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TempIdCreateService {

    private final CanvasSseEmitterFinder canvasSseEmitterFinder;
    private final SketchFindDao sketchFindDao;

    public Response getSubjectImageAndCreateTempId(Long subjectId){
        String tempId = createTempIdForSse();
        String subjectSketch = getPrimarySubjectSketch(subjectId);
        return Response.of(SseCode.TEMP_ID_GENERATE_SUCCESS, new CreateTempIdResponse(subjectId, subjectSketch, tempId));
    }

    private String getPrimarySubjectSketch(Long subjectId){
        List<Sketch> sketches = sketchFindDao.findSketchBySubjectId(subjectId);
        Sketch randomSketch = sketches.get(new Random().nextInt(sketches.size()));
        return randomSketch.getSketchImageUrl();
    }

    private String createTempIdForSse(){
        String tempId = generateUUID();
        while (canvasSseEmitterFinder.findByTempId(tempId) != null){
            tempId = generateUUID();
        }
        return tempId;
    }

    protected String generateUUID(){
        return UUID.randomUUID().toString();
    }

}
