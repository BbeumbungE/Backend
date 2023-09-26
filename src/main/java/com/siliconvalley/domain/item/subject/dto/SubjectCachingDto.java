package com.siliconvalley.domain.item.subject.dto;

import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectCachingDto {

    private Long subjectId;
    private String subjectName;
    private String subjectImage;

    @Builder
    public SubjectCachingDto(Subject subject){
        this.subjectId = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.subjectImage = subject.getSubjectImage();
    }

}
