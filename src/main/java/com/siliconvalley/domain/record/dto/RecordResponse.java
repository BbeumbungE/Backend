package com.siliconvalley.domain.record.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.stage.dto.StageResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordResponse {

    private Long id;
    private int score;

    public RecordResponse(final Record record) {
        this.id = record.getId();
        this.score = record.getScore();
    }
}
