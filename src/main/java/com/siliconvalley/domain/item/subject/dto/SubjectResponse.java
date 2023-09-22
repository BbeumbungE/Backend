package com.siliconvalley.domain.item.subject.dto;

import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectResponse {
    private Long id;
    private String subjectName;
    private String subjectImage;
    private SketchResponseList sketchList;

    public SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.subjectImage = subject.getSubjectImage();
        this.sketchList = new SketchResponseList(subject);
    }
}
