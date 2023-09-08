package com.siliconvalley.domain.item.subject.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.item.sketch.dto.SketchResponseList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectResponse {
    private Long id;
    private String subjectName;
    private String subjectImage;

    @QueryProjection
    public SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.subjectImage = subject.getSubjectImage();
    }
}
