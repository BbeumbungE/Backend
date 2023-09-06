package com.siliconvalley.domain.item.sketch.dto;

import com.siliconvalley.domain.item.sketch.domain.Sketch;
import com.siliconvalley.domain.item.sketch.dto.SketchResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SketchResponseList {

    List<SketchResponse> sketchList;

    public SketchResponseList(final List<Sketch> sketchList) {
        this.sketchList = sketchList.stream()
                .map(sketch -> new SketchResponse(sketch))
                .collect(Collectors.toList());
    }
}
