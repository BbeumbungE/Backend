package com.siliconvalley.domain.item.stage.dao;

import com.siliconvalley.domain.item.stage.domain.Stage;
import com.siliconvalley.domain.item.stage.dto.StageSubjectResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StageSubjectFindDao {

    private final StageRepository stageRepository;

    public StageSubjectResponseList getStageItemList() {
        List<Stage> stageList = stageRepository.findAll();
        return new StageSubjectResponseList(stageList);
    }
}
