package com.siliconvalley.domain.record.dto;

import com.siliconvalley.domain.stage.dto.StageWithRecordResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordResponseWithHighestClearedStageNumber {

    private int highestClearedStageNumber;
    private List<StageWithRecordResponse> record;

    public RecordResponseWithHighestClearedStageNumber(int highestClearedStageNumber, List<StageWithRecordResponse> record) {
        this.highestClearedStageNumber = highestClearedStageNumber;
        this.record = record;
    }
}
