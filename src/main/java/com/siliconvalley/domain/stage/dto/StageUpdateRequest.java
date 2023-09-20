package com.siliconvalley.domain.stage.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageUpdateRequest {

    @Valid
    private int point;

    @Valid
    private int timeLimit;

    StageUpdateRequest(
            @Valid int point,
            @Valid int timeLimit
    ) {
        this.point = point;
        this.timeLimit = timeLimit;
    }
}
