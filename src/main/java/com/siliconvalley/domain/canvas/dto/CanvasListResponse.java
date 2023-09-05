package com.siliconvalley.domain.canvas.dto;

import com.siliconvalley.domain.canvas.domain.Canvas;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CanvasListResponse {
    private Long canvasId;
    private String imageUrl;

    public CanvasListResponse(Canvas canvas){
        this.canvasId = canvas.getId();
        this.imageUrl = canvas.getCanvas();
    }
}
