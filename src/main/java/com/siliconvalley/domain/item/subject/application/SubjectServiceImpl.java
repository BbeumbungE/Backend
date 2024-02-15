package com.siliconvalley.domain.item.subject.application;

import com.siliconvalley.domain.item.subject.code.SketchCode;
import com.siliconvalley.domain.item.subject.dao.SketchFindDao;
import com.siliconvalley.domain.item.subject.dao.SketchRepository;
import com.siliconvalley.domain.item.subject.domain.Sketch;
import com.siliconvalley.domain.item.subject.dto.SketchResponse;
import com.siliconvalley.domain.item.subject.dto.SubjectResponse;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService{

    private final SketchFindDao sketchFindDao;
    @Override
    @Transactional(readOnly = true)
    public Response getRandomSketchBySubjectId(Long subjectId){
        List<Sketch> sketches = sketchFindDao.findSketchBySubjectId(subjectId);
        Sketch randomSketch = sketches.get(new Random().nextInt(sketches.size()));
        return Response.of(CommonCode.GOOD_REQUEST, new SketchResponse(randomSketch));
    }
}
