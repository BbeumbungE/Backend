package com.siliconvalley.domain.canvas.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CanvasConvertResponse {
    private Long canvasId;
    private String topPost;
    public CanvasConvertResponse(Long canvasId, String topPost){
        this.canvasId = canvasId;
        this.topPost = topPost;
    }
}
