package com.siliconvalley.domain.canvas.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CanvasResponse {
    private Long canvasId;
    private String canvasUrl;
    private String sketchUrl;
    public CanvasResponse(Long canvasId, String canvasUrl, String sketchUrl){
        this.canvasId = canvasId;
        this.canvasUrl = canvasUrl;
        this.sketchUrl = sketchUrl;
    }

}
