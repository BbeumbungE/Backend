package com.siliconvalley.domain.stage.application;

import com.siliconvalley.domain.stage.code.StageCode;
import com.siliconvalley.domain.stage.dao.StageFindDao;
import com.siliconvalley.domain.stage.domain.Stage;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.stage.dto.StageUpdateRequest;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StageUpdateService {

    private final SubjectFindDao subjectFindDao;
    private final StageFindDao stageFindDao;

    public Response updateStageSubject(Long subjectId, Long stageId) {
        Subject subject = subjectFindDao.findById(subjectId);
        Stage stage = stageFindDao.findById(stageId);
        stage.addSubject(subject);
        return Response.of(StageCode.UPDATE_SUCCESS, null);
    }

    public Response updateStageInfo(Long stageId,StageUpdateRequest dto) {
        Stage stage = stageFindDao.findById(stageId);
        stage.updateStage(dto.getPoint(), dto.getTimeLimit());
        return Response.of(StageCode.UPDATE_SUCCESS, null);
    }
}
