package com.siliconvalley.domain.record.dto;

import com.siliconvalley.domain.point.dto.RewardPointInfo;
import com.siliconvalley.domain.record.domain.Record;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordResponseWithPointInfo {

    private Long id;
    private int score;
    private RewardPointInfo pointInfo;

    public RecordResponseWithPointInfo(Record record, RewardPointInfo rewardPointInfo) {
        this.id = record.getId();
        this.score = record.getScore();
        this.pointInfo = rewardPointInfo;
    }
}
