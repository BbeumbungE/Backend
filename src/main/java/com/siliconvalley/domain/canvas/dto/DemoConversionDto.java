package com.siliconvalley.domain.canvas.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DemoConversionDto {

    private String canvasUrl;

    public DemoConversionDto(String canvasUrl){
        this.canvasUrl = canvasUrl;
    }

}
