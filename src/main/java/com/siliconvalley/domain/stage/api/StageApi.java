package com.siliconvalley.domain.stage.api;

import com.siliconvalley.domain.stage.application.StageCreateService;
import com.siliconvalley.domain.stage.application.StageUpdateService;
import com.siliconvalley.domain.stage.dao.StageFindDao;
import com.siliconvalley.domain.stage.dto.StageCreateRequest;
import com.siliconvalley.domain.stage.dto.StageUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/stages")
@RequiredArgsConstructor
public class StageApi {

    private final StageFindDao stageFindDao;
    private final StageCreateService stageCreateService;
    private final StageUpdateService stageUpdateService;

    @GetMapping()
    public ResponseEntity getAllStage(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(stageFindDao.getAllStage(pageable));
    }


    @PostMapping
    public ResponseEntity createStage(
            @RequestBody @Valid StageCreateRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stageCreateService.createStage(dto));
    }
    @GetMapping("/{stageId}")
    public ResponseEntity getStageInfo(
            @PathVariable(name = "stageId") Long stageId) {
        return ResponseEntity.status(HttpStatus.OK).body(stageFindDao.getStage(stageId));
    }


    @PatchMapping("/{stageId}")
    public ResponseEntity updateStageInfo(
            @PathVariable(name = "stageId") Long stageId,
            @RequestBody @Valid StageUpdateRequest dto) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(stageUpdateService.updateStageInfo(stageId, dto));
    }

    @PatchMapping("/{stageId}/subjects/{subjectId}")
    public ResponseEntity setSubjectToStage(
            @PathVariable(name = "subjectId") Long subjectId,
            @PathVariable(name = "stageId") Long stageId
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(stageUpdateService.updateStageSubject(subjectId, stageId));
    }
}
