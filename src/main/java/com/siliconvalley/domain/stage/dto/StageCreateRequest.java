package com.siliconvalley.domain.stage.dto;

import com.siliconvalley.domain.stage.domain.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageCreateRequest {

    @Valid
    private int stageNum;

    @Valid
    private int point;

    @Valid
    private int timeLimit;

    StageCreateRequest(
            @Valid int stageNum,
            @Valid int point,
            @Valid int timeLimit
    ) {
        this.stageNum = stageNum;
        this.point = point;
        this.timeLimit = timeLimit;
    }

    public Stage toEntity() {
        return Stage.builder()
                .stageNum(stageNum)
                .point(point)
                .timeLimit(timeLimit)
                .build();
    }
}
