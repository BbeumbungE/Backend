package com.siliconvalley.domain.pix2pix.dto;

import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Pix2PixAndSubjectDto {

    private Long subjectId;
    private String subjectName;
    private String subjectImage;
    private Long modelId;
    private String modelName;

    public Pix2PixAndSubjectDto(Subject subject){
        this.subjectId = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.subjectImage = subject.getSubjectImage();
        this.modelId = subject.getPix2Pix().getId();
        this.modelName = subject.getPix2Pix().getModelName();
    }

    public Subject toSubjectEntity(){
        return Subject.builder()
                .subjectName(subjectName)
                .build();
    }

}

