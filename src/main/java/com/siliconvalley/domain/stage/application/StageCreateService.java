package com.siliconvalley.domain.stage.application;

import com.siliconvalley.domain.stage.code.StageCode;
import com.siliconvalley.domain.stage.dao.StageRepository;
import com.siliconvalley.domain.stage.domain.Stage;
import com.siliconvalley.domain.stage.dto.StageCreateRequest;
import com.siliconvalley.domain.stage.dto.StagePostSuccessResponse;
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
