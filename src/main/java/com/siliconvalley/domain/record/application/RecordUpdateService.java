package com.siliconvalley.domain.record.application;

import com.siliconvalley.domain.google.service.VisionDetectingService;
import com.siliconvalley.domain.point.application.StageRewardApp;
import com.siliconvalley.domain.point.dto.RewardPointInfo;
import com.siliconvalley.domain.record.code.RecordCode;
import com.siliconvalley.domain.record.dao.RecordFindDao;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.dto.RecordResponseWithPointInfo;
import com.siliconvalley.domain.record.dto.RecordUpdateRequest;
import com.siliconvalley.domain.stage.domain.Score;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordUpdateService {

    private final RecordFindDao recordFindDao;
    private final StageRewardApp stageRewardApp;
    private final VisionDetectingService visionDetectingService;

    public Response evaluateCanvasAndupdateRecord(Long recordId, RecordUpdateRequest dto) {
        Record record = recordFindDao.findById(recordId);
        Score score = visionDetectingService.calculateCanvasScore(dto.getCanvasId());

        RewardPointInfo rewardPointInfo = stageRewardApp.getStageRewardPoint(
                record.getProfile(),
                record.getStage().getPoint(),
                score.getScoreValue(),
                record.getScore()
        ); // 리워드 포인트 받기

        record.updateScore(score.getScoreValue());

        return Response.of(RecordCode.UPDATE_SUCCESS, new RecordResponseWithPointInfo(record, rewardPointInfo));
    }
}
