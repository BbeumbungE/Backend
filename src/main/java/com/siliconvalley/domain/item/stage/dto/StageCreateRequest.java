package com.siliconvalley.domain.item.stage.dto;

import com.siliconvalley.domain.item.stage.domain.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageCreateRequest {

    @Valid
    private int stage;

    @Valid
    private int point;

    StageCreateRequest(
            @Valid int stage,
            @Valid int point
    ) {
        this.stage = stage;
        this.point = point;
    }

    public Stage toEntity() {
        return Stage.builder()
                .stage(stage)
                .point(point)
                .build();
    }
}
