package com.siliconvalley.domain.item.subject.application;

import com.siliconvalley.domain.item.subject.code.SketchCode;
import com.siliconvalley.domain.item.subject.dao.SketchRepository;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Sketch;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.item.subject.dto.SketchPostSuccessResponse;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SketchCreateService {

    private final SubjectFindDao subjectFindDao;
    private final SketchRepository sketchRepository;

    public Response createSketch(Long subjectId, String sketchImageUrl) {
        Subject subject = subjectFindDao.findById(subjectId);
        Sketch sketch = Sketch.toEntity(sketchImageUrl, subject);
        sketchRepository.save(sketch);
        return Response.of(SketchCode.CREATE_SUCCESS, new SketchPostSuccessResponse(sketch));
    }
}
