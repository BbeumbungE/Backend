package com.siliconvalley.domain.item.stage.api;

import com.siliconvalley.domain.item.subject.application.SubjectCreateService;
import com.siliconvalley.domain.item.stage.dao.StageSubjectFindDao;
import com.siliconvalley.domain.item.stage.dto.StageSubjectCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/stages/subjects")
@RequiredArgsConstructor
public class StageSubjectApi {

    private final SubjectCreateService subjectCreateService;
    private final StageSubjectFindDao stageSubjectFindDao;

    @PostMapping
    public ResponseEntity createStageSubject(@RequestBody @Valid StageSubjectCreateRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectCreateService.createStageSubject(dto));
    }

    @GetMapping()
    public ResponseEntity getAllStageItem(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(stageSubjectFindDao.getStageItemList(pageable));
    }
}
