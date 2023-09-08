package com.siliconvalley.domain.canvas.dto;

import com.siliconvalley.domain.canvas.domain.Canvas;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CanvasResponse {
    private Long canvasId;
    private String canvasUrl;
    private String sketchUrl;
    public CanvasResponse(Canvas canvas){
        this.canvasId = canvas.getId();
        this.canvasUrl = canvas.getCanvas();
        this.sketchUrl = canvas.getSketch();
    }

}
