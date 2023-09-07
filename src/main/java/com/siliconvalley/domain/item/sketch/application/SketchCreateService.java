package com.siliconvalley.domain.item.sketch.application;

import com.siliconvalley.domain.item.sketch.code.SketchCode;
import com.siliconvalley.domain.item.sketch.dto.SketchPostSuccessResponse;
import com.siliconvalley.domain.item.sketch.dao.SketchRepository;
import com.siliconvalley.domain.item.sketch.dto.SketchCreateRequest;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SketchCreateService {

    private final SketchRepository sketchRepository;
    private final SubjectFindDao subjectFindDao;

    public Response createSketch(Long subjectId, SketchCreateRequest dto) {
        Subject subject = subjectFindDao.findById(subjectId);
        return Response.of(SketchCode.CREATE_SUCCESS, new SketchPostSuccessResponse(sketchRepository.save(dto.toEntity(subject))));
    }
}
