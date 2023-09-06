package com.siliconvalley.domain.item.subject.application;

import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.ItemPostSuccessResponse;
import com.siliconvalley.domain.item.stage.dao.StageRepository;
import com.siliconvalley.domain.item.stage.domain.Stage;
import com.siliconvalley.domain.item.stage.dto.StagePostSuccessResponse;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.item.item.dto.RankSubjectItemCreateRequest;
import com.siliconvalley.domain.item.stage.dto.StageSubjectCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectCreateService {

    private final ItemRepository itemRepository;
    private final StageRepository stageRepository;

    public ItemPostSuccessResponse createRankSubject(RankSubjectItemCreateRequest dto) {
        Item item = dto.toItemEntity();
        Subject subject = dto.toSubjectEntity();

        // Item과 Subject 객체를 연결
        item.setSubject(subject);

        // Item이 저장될 때 Subject 자동 저장
        itemRepository.save(item);

        return new ItemPostSuccessResponse(item);
    }

    public StagePostSuccessResponse createStageSubject(StageSubjectCreateRequest dto) {
        Stage stage = dto.toStageEntity();
        Subject subject = dto.toSubjectEntity();

        // Stage과 Subject 객체를 연결
        stage.setSubject(subject);

        // Stage이 저장될 때 Subject 자동 저장
        stageRepository.save(stage);

        return new StagePostSuccessResponse(stage);
    }
}
