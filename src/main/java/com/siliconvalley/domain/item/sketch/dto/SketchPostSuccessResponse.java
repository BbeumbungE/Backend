package com.siliconvalley.domain.item.sketch.dto;

import com.siliconvalley.domain.item.sketch.domain.Sketch;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SketchPostSuccessResponse {

    private Long id;

    public SketchPostSuccessResponse(Sketch sketch) {
        this.id = sketch.getId();
    }
}
