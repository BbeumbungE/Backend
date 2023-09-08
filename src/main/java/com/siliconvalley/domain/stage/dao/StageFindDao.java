package com.siliconvalley.domain.stage.dao;

import com.siliconvalley.domain.record.dao.RecordFindDao;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.stage.domain.Stage;
import com.siliconvalley.domain.stage.dto.StageResponse;
import com.siliconvalley.domain.stage.dto.StageWithRecordResponse;
import com.siliconvalley.domain.stage.exception.StageNotFoundException;
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
    private final RecordFindDao recordFindDao;

    public Stage findById(Long stageId) {
        Optional<Stage> stageOptional = stageRepository.findById(stageId);
        stageOptional.orElseThrow(() -> new StageNotFoundException(stageId));
        return stageOptional.get();
    }

    public Response getAllStage(Pageable pageable) {
        Page<Stage> stageList = stageRepository.findAll(pageable);
        List<StageResponse> stageSubjectList = stageList.stream().map(stage -> new StageResponse(stage)).collect(Collectors.toList());
        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(stageSubjectList, stageList));
    }

    public Response getAllStageWithRecord(Long profileId, Pageable pageable) {
        Page<Stage> stageList = stageRepository.findAll(pageable);
        List<StageWithRecordResponse> stageWithRecordList = stageList.stream()
                        .map(stage -> {
                            Optional<Record> recordOptional = recordFindDao.findByProfileIdAndStageId(profileId, stage.getId());
                            return recordOptional.isPresent() ? new StageWithRecordResponse(stage, recordOptional.get()) : new StageWithRecordResponse(stage);
                        })
                        .collect(Collectors.toList());

        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(stageWithRecordList, stageList));
    }
}
