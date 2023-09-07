package com.siliconvalley.domain.item.stage.application;

import com.siliconvalley.domain.item.stage.code.StageCode;
import com.siliconvalley.domain.item.stage.dao.StageFindDao;
import com.siliconvalley.domain.item.stage.domain.Stage;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
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

    public Response updateStage(Long subjectId, Long stageId) {
        Subject subject = subjectFindDao.findById(subjectId);
        Stage stage = stageFindDao.findById(stageId);
        stage.setSubject(subject);
        return Response.of(StageCode.UPDATE_SUCCESS, null);
    }
}
