package com.siliconvalley.domain.item.sketch.application;

import com.siliconvalley.domain.item.sketch.dto.SketchPostSuccessResponse;
import com.siliconvalley.domain.item.sketch.dao.SketchRepository;
import com.siliconvalley.domain.item.sketch.dto.SketchCreateRequest;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SketchCreateService {

    private final SketchRepository sketchRepository;
    private final SubjectFindDao subjectFindDao;

    public SketchPostSuccessResponse createSketch(Long subjectId, SketchCreateRequest dto) {
        Subject subject = subjectFindDao.findById(subjectId);
        return new SketchPostSuccessResponse(sketchRepository.save(dto.toEntity(subject)));
    }
}
