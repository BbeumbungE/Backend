package com.siliconvalley.domain.canvas.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CanvasDto {

    private Long canvasId;
    private String canvasUrl;

    @Builder
    public CanvasDto(Long canvasId, String canvasUrl){
        this.canvasId = canvasId;
        this.canvasUrl = canvasUrl;
    }
}