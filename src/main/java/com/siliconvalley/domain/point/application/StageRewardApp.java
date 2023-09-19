package com.siliconvalley.domain.point.application;

import com.siliconvalley.domain.point.domain.Point;
import com.siliconvalley.domain.point.dto.RewardPointInfo;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class StageRewardApp {
    public RewardPointInfo getStageRewardPoint(Profile profile, int stageRewardPoint, int score, int previousScore) {
        Point point = profile.getPoint();
        int rewardPoint = (score > previousScore) ? (score - previousScore) * stageRewardPoint / 3 : 0;
        point.updatePoint(point.getPoint() + rewardPoint);
        return new RewardPointInfo(point, rewardPoint);
    }
}
