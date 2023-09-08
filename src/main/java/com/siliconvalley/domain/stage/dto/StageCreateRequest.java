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

    StageCreateRequest(
            @Valid int stageNum,
            @Valid int point
    ) {
        this.stageNum = stageNum;
        this.point = point;
    }

    public Stage toEntity() {
        return Stage.builder()
                .stageNum(stageNum)
                .point(point)
                .build();
    }
}
