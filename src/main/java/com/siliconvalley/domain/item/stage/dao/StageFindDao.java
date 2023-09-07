package com.siliconvalley.domain.item.stage.dao;

import com.siliconvalley.domain.item.stage.domain.Stage;
import com.siliconvalley.domain.item.stage.dto.StageResponse;
import com.siliconvalley.domain.item.stage.exception.StageNotFoundException;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.common.dto.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StageFindDao {

    private final StageRepository stageRepository;

    public Stage findById(Long stageId) {
        Optional<Stage> stageOptional = stageRepository.findById(stageId);
        stageOptional.orElseThrow(() -> new StageNotFoundException(stageId));
        return stageOptional.get();
    }

    public Response getStageList(Pageable pageable) {
        Page<Stage> stageList = stageRepository.findAll(pageable);
        List<StageResponse> stageSubjectList = stageList.stream().map(stage -> new StageResponse(stage)).collect(Collectors.toList());
        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(stageSubjectList, stageList));
    }
}
