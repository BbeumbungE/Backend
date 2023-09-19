package com.siliconvalley.domain.rabbitMQ.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SketchConversionResponse {
    private String canvasUrl;
    private String canvasName;
    private Long canvasId;
    private String status;

    public SketchConversionResponse(String canvasUrl, Long canvasId, String status, String canvasName){
        this.canvasId = canvasId;
        this.canvasUrl = canvasUrl;
        this.status = status;
        this.canvasName = canvasName;
    }
}
