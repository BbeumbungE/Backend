package com.siliconvalley.domain.pix2pix.dto;

import com.siliconvalley.domain.pix2pix.domain.Pix2Pix;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UseModelDto {

    private Long subjectId;
    private Long pix2PixId;
    private String subjectName;
    private String modelName;

    @Builder
    public UseModelDto(Pix2Pix pix2Pix){
        this.subjectId = pix2Pix.getSubject().getId();
        this.pix2PixId = pix2Pix.getId();
        this.subjectName = pix2Pix.getSubject().getSubjectName();
        this.modelName = pix2Pix.getModelName();
    }
}
