package com.siliconvalley.domain.item.stage.api;

import com.siliconvalley.domain.item.stage.application.StageCreateService;
import com.siliconvalley.domain.item.stage.application.StageUpdateService;
import com.siliconvalley.domain.item.stage.dao.StageFindDao;
import com.siliconvalley.domain.item.stage.dto.StageCreateRequest;
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
        return ResponseEntity.status(HttpStatus.OK).body(stageFindDao.getStageList(pageable));
    }


    @PostMapping
    public ResponseEntity createStage(
            @RequestBody @Valid StageCreateRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stageCreateService.createStage(dto));
    }
}
