package com.siliconvalley.domain.item.subject.api;

import com.siliconvalley.domain.stage.application.StageUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectApi {

    private final StageUpdateService stageUpdateService;

    @PatchMapping("/{subjectId}/stages/{stageId}")
    public ResponseEntity setSubjectToStage(
            @PathVariable(name = "subjectId") Long subjectId,
            @PathVariable(name = "stageId") Long stageId
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(stageUpdateService.updateStage(subjectId, stageId));
    }
}
