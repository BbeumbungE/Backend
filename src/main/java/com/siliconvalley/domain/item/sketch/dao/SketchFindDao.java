package com.siliconvalley.domain.item.sketch.dao;

import com.siliconvalley.domain.item.sketch.dto.SketchResponseList;
import com.siliconvalley.domain.item.sketch.domain.Sketch;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SketchFindDao {

    private final SketchRepository sketchRepository;

    public Response getAllsketches(Long subjectId) {
        List<Sketch> sketchList = sketchRepository.findBySubjectId(subjectId);
        return Response.of(CommonCode.GOOD_REQUEST,new SketchResponseList(sketchList));
    }
}
