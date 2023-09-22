package com.siliconvalley.domain.item.subject.dto;

import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SketchResponseList {

    private List<SketchResponse> sketchList;

    public SketchResponseList (Subject subject) {
        this.sketchList = subject.getSketchList().stream()
                .map(sketch -> new SketchResponse(sketch))
                .collect(Collectors.toList());
    }
}
