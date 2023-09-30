package com.siliconvalley.domain.rabbitMQ.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemoConversionResponse {
    private String canvasUrl;
    private String status;
    private String tempId;

    public DemoConversionResponse(String canvasUrl, String status, String tempId){
        this.canvasUrl = canvasUrl;
        this.status = status;
        this.tempId = tempId;
    }
}
