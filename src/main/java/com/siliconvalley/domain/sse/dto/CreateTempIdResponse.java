package com.siliconvalley.domain.sse.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateTempIdResponse {

    private Long subjectId;
    private String subjectSketch;
    private String tempId;

    public CreateTempIdResponse(Long subjectId, String subjectSketch, String tempId){
        this.subjectId = subjectId;
        this.subjectSketch = subjectSketch;
        this.tempId = tempId;
    }
}
