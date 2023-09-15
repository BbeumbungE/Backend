package com.siliconvalley.domain.canvas.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertEventDto {

    private Long canvasId;
    private String canvasUrl;

    public ConvertEventDto(Long canvasId, String canvasUrl){
        this.canvasId = canvasId;
        this.canvasUrl = canvasUrl;
    }

}
