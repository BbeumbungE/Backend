package com.siliconvalley.domain.rabbitMQ.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SketchConversionRequest {
    private String sketchUrl;
    private Long canvasId;
    private Long profileId;
    private String modelName;

    public SketchConversionRequest(String sketchUrl, Long canvasId, Long profileId, String modelName){
        this.sketchUrl = sketchUrl;
        this.canvasId = canvasId;
        this.profileId = profileId;
        this.modelName = modelName;
    }
}
