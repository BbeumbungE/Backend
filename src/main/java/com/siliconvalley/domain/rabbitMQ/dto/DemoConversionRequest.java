package com.siliconvalley.domain.rabbitMQ.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemoConversionRequest {
    private String sketchUrl;
    private String modelName;
    private String tempId;

    public DemoConversionRequest(String sketchUrl, String modelName, String tempId){
        this.sketchUrl = sketchUrl;
        this.modelName = modelName;
        this.tempId = tempId;
    }
}
