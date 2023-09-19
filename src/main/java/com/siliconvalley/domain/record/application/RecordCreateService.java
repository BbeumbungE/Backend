package com.siliconvalley.domain.record.application;

import com.siliconvalley.domain.point.application.StageRewardApp;
import com.siliconvalley.domain.point.dto.RewardPointInfo;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.record.code.RecordCode;
import com.siliconvalley.domain.record.dao.RecordFindDao;
import com.siliconvalley.domain.record.dao.RecordRepository;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.dto.RecordCreateRequest;
import com.siliconvalley.domain.record.dto.RecordPostSuccessResponse;
import com.siliconvalley.domain.record.exception.RecordAlreadyExist;
import com.siliconvalley.domain.stage.dao.StageFindDao;
import com.siliconvalley.domain.stage.domain.Stage;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordCreateService {

    private final RecordRepository recordRepository;
    private final RecordFindDao recordFindDao;
    private final ProfileFindDao profileFindDao;
    private final StageFindDao stageFindDao;
    private final StageRewardApp stageRewardApp;

    public Response createRecord(Long profileId, Long stageId,RecordCreateRequest dto) {
        Profile profile = profileFindDao.findById(profileId);
        Stage stage = stageFindDao.findById(stageId);

        if (recordFindDao.findByProfileId(profileId).isPresent()) {
            throw new RecordAlreadyExist(stage.getStageNum() + "번 스테이지 기록");
        }

        Record record = dto.toEntity(profile, stage);
        recordRepository.save(record);
        RewardPointInfo rewardPointInfo = stageRewardApp.getStageRewardPoint(
                profile,
                stage.getPoint(),
                record.getScore(),
                0
        ); // 리워드 포인트 받기
        return Response.of(RecordCode.CREATE_SUCCESS, new RecordPostSuccessResponse(record, rewardPointInfo));
    }
}


