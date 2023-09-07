package com.siliconvalley.domain.item.stage.application;

import com.siliconvalley.domain.item.stage.code.StageCode;
import com.siliconvalley.domain.item.stage.dao.StageRepository;
import com.siliconvalley.domain.item.stage.domain.Stage;
import com.siliconvalley.domain.item.stage.dto.StageCreateRequest;
import com.siliconvalley.domain.item.stage.dto.StagePostSuccessResponse;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StageCreateService {

    private final StageRepository stageRepository;
    public Response createStage(StageCreateRequest dto) {
        Stage stage = dto.toEntity();
        stageRepository.save(stage);
        return Response.of(StageCode.CREATE_SUCCESS, new StagePostSuccessResponse(stage));
    }
}
