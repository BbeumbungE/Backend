package com.siliconvalley.domain.point.dto;

import com.siliconvalley.domain.point.domain.Point;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RewardPointInfo {

    private Long previousPoint;
    private Long currentPoint;
    private int rewardPoint;

    public RewardPointInfo(Point point, int rewardPoint) {
        this.previousPoint = point.getPoint() - rewardPoint;
        this.currentPoint = point.getPoint();
        this.rewardPoint = rewardPoint;
    }
}
