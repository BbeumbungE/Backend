package com.siliconvalley.domain.record.dto;

import com.siliconvalley.domain.point.dto.RewardPointInfo;
import com.siliconvalley.domain.record.domain.Record;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordPostSuccessResponse {

    private Long id;
    private RewardPointInfo pointInfo;

    public RecordPostSuccessResponse(Record record, RewardPointInfo rewardPointInfo) {
        this.id = record.getId();
        this.pointInfo = rewardPointInfo;
    }
}
