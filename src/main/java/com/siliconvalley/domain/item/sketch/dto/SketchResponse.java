package com.siliconvalley.domain.item.sketch.dto;

import com.siliconvalley.domain.item.sketch.domain.Sketch;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SketchResponse {

    private Long id;
    private Integer level;
    private String sketch;

    public SketchResponse(Sketch sketch) {
        this.id = sketch.getId();
        this.level = sketch.getLevel();
        this.sketch = sketch.getSketch();
    }
}
