package com.siliconvalley.domain.stage.dao;

import com.siliconvalley.domain.item.myitem.dao.MyItemFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.record.dao.RecordFindDao;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.dto.RecordResponseWithHighestClearedStageNumber;
import com.siliconvalley.domain.record.dto.RecordResponseWithPointInfo;
import com.siliconvalley.domain.stage.domain.Stage;
import com.siliconvalley.domain.stage.dto.StageResponse;
import com.siliconvalley.domain.stage.dto.StageWithRecordResponse;
import com.siliconvalley.domain.stage.exception.StageNotFoundException;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.common.dto.page.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    public Response getStage(Long stageId) {
        Stage stage = findById(stageId);
        return Response.of(CommonCode.GOOD_REQUEST, (stage.getSubject() == null) ? new StageResponse(stage) : new StageResponse(stage, stage.getSubject()));
    }

    public Response getAllStage(Pageable pageable) {
        final int stageViewSize = 3;
        Page<Stage> stageList = stageRepository.findAll(pageable);
        List<StageResponse> stageSubjectList = stageList.stream()
                        .map(stage -> (stage.getSubject() == null) ? new StageResponse(stage) : new StageResponse(stage, stage.getSubject()))
                        .collect(Collectors.toList());
        while (stageSubjectList.size() < stageViewSize && !stageSubjectList.isEmpty()){
            int stageNumber = stageSubjectList.get(stageSubjectList.size()-1).getStageNum()+1;
            stageSubjectList.add(new StageResponse(stageNumber));
        }
        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(stageSubjectList, stageList));
    }

    public Response getAllStageWithRecord(Long profileId, Pageable pageable) {
        Page<Stage> stageList = stageRepository.findAll(pageable);

        int highestClearedStageNumber = 0;
        List<StageWithRecordResponse> stageWithRecordList = new LinkedList<>();

        for (Stage stage : stageList) {
            Optional<Record> recordOptional = recordFindDao.findByProfileIdAndStageId(profileId, stage.getId());

            if (recordOptional.isPresent()) {
                stageWithRecordList.add(new StageWithRecordResponse(stage, recordOptional.get()));
            } else {
                if (highestClearedStageNumber == 0) highestClearedStageNumber = stage.getStageNum() - 1;
                stageWithRecordList.add(new StageWithRecordResponse(stage));
            }
        }

        while (stageWithRecordList.size() < pageable.getPageSize() && !stageWithRecordList.isEmpty()){

            int stageNumber = stageWithRecordList.get(stageWithRecordList.size()-1).getStageNum()+1;
            stageWithRecordList.add(new StageWithRecordResponse(stageNumber));
        }

        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(new RecordResponseWithHighestClearedStageNumber(highestClearedStageNumber, stageWithRecordList), stageList));
    }

    public Response getStageWithRecord(Long profileId, Long stageId) {
        Stage stage = findById(stageId);
        Optional<Record> recordOptional = recordFindDao.findByProfileIdAndStageId(profileId, stageId);

        return Response.of(CommonCode.GOOD_REQUEST,
                (recordOptional.isPresent()) ?
                        new StageWithRecordResponse(stage, recordOptional.get())
                        : new StageWithRecordResponse(stage));
    }
}
