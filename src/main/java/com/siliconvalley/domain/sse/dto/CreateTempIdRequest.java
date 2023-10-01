package com.siliconvalley.domain.sse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateTempIdRequest {
    private Long subjectId;

    public CreateTempIdRequest(Long subjectId){
        this.subjectId = subjectId;
    }
}
