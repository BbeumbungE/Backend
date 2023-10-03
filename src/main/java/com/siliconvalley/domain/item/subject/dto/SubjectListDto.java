package com.siliconvalley.domain.item.subject.dto;

import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectListDto {

    private String subjectName;
    private Long subjectId;

    public SubjectListDto(Subject subject){
        this.subjectName = subject.getSubjectName();
        this.subjectId = subject.getId();
    }

}
