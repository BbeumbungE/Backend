package com.siliconvalley.domain.item.subject.application;

import com.siliconvalley.global.common.dto.Response;

public interface SubjectService {
    Response getRandomSketchBySubjectId(Long subjectId);
}
