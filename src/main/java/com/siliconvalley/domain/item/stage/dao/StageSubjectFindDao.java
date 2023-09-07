package com.siliconvalley.domain.item.stage.dao;

import com.siliconvalley.domain.item.stage.domain.Stage;
import com.siliconvalley.domain.item.stage.dto.StageSubjectResponse;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.common.dto.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StageSubjectFindDao {

    private final StageRepository stageRepository;

    public Response getStageItemList(Pageable pageable) {
        Page<Stage> stageList = stageRepository.findAll(pageable);
        List<StageSubjectResponse> stageSubjectList = stageList.stream().map(stage -> new StageSubjectResponse(stage)).collect(Collectors.toList());
        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(stageSubjectList, stageList));
    }
}
