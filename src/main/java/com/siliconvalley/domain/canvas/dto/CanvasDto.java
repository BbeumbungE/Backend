package com.siliconvalley.domain.canvas.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CanvasDto {

    private Long id;
    private String canvas;

    @Builder
    public CanvasDto(Long id, String canvas){
        this.id = id;
        this.canvas = canvas;
    }
}