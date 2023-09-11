package com.siliconvalley.domain.stage.api;

import com.siliconvalley.domain.stage.application.StageCreateService;
import com.siliconvalley.domain.stage.dao.StageFindDao;
import com.siliconvalley.domain.stage.dto.StageCreateRequest;
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

    @GetMapping()
    public ResponseEntity getAllStage(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(stageFindDao.getAllStage(pageable));
    }

    @PostMapping
    public ResponseEntity createStage(
            @RequestBody @Valid StageCreateRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stageCreateService.createStage(dto));
    }
}
