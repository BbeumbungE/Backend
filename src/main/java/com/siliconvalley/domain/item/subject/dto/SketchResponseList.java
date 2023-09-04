package com.siliconvalley.domain.item.subject.dto;

import com.siliconvalley.domain.item.subject.domain.Sketch;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SketchResponseList {

    List<SketchResponse> sketchResponseList;

    public SketchResponseList(final List<Sketch> sketchList) {
        this.sketchResponseList = sketchList.stream()
                .map(sketch -> new SketchResponse(sketch))
                .collect(Collectors.toList());
    }
}
