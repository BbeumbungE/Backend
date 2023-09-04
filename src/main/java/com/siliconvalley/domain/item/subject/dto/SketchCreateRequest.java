package com.siliconvalley.domain.item.subject.dto;

import com.siliconvalley.domain.item.subject.domain.Sketch;
import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SketchCreateRequest {

    @Valid
    private Integer level;

    @Valid
    private String sketch;

    SketchCreateRequest(
            @Valid Integer level,
            @Valid String sketch
    ) {
        this.level = level;
        this.sketch = sketch;
    }

    public Sketch toEntity(Subject subject) {
        return Sketch.builder()
                .level(level)
                .sketch(sketch)
                .subject(subject)
                .build();
    }
}
