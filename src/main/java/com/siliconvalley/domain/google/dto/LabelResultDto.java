package com.siliconvalley.domain.google.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LabelResultDto {

    private String label;
    private Float confidence;;

    public LabelResultDto(String label, Float confidence){
        this.label = label;
        this.confidence = confidence;
    }
}
