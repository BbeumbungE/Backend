package com.siliconvalley.domain.item.stage.dto;

import com.siliconvalley.domain.item.stage.domain.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageResponse {

    private Long id;
    private int stage;
    private int point;

    public StageResponse(final Stage stage) {
        this.id = stage.getId();
        this.stage = stage.getStage();
        this.point = stage.getPoint();
    }
}
