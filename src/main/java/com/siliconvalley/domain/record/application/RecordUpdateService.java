package com.siliconvalley.domain.record.application;

import com.siliconvalley.domain.point.application.StageRewardApp;
import com.siliconvalley.domain.point.dto.RewardPointInfo;
import com.siliconvalley.domain.record.code.RecordCode;
import com.siliconvalley.domain.record.dao.RecordFindDao;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.dto.RecordUpdateRequest;
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

    public Response updateRecord(Long recordId, RecordUpdateRequest dto) {
        Record record = recordFindDao.findById(recordId);
        RewardPointInfo rewardPointInfo = stageRewardApp.getStageRewardPoint(
                record.getProfile(),
                record.getStage().getPoint(),
                dto.getScore(),
                record.getScore()
        );
        record.updateScore(dto.getScore());
        return Response.of(RecordCode.UPDATE_SUCCESS, rewardPointInfo);
    }
}
