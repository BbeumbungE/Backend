package com.siliconvalley.domain.item.subject.dto;

import com.siliconvalley.domain.item.subject.domain.Sketch;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SketchResponse {

    private Long sketchId;
    private String sketchImageUrl;

    public SketchResponse(Sketch sketch) {
        this.sketchId = sketch.getId();
        this.sketchImageUrl = sketch.getSketchImageUrl();
    }
}
