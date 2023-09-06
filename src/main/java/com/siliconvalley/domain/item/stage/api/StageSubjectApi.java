package com.siliconvalley.domain.item.stage.api;

import com.siliconvalley.domain.item.item.code.ItemCode;
import com.siliconvalley.domain.item.subject.application.SubjectCreateService;
import com.siliconvalley.domain.item.stage.dao.StageSubjectFindDao;
import com.siliconvalley.domain.item.stage.dto.StageSubjectCreateRequest;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
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
        Response response = Response.of(ItemCode.CREATE_SUCCESS, subjectCreateService.createStageSubject(dto));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity getAllStageItem() {
        Response response = Response.of(CommonCode.GOOD_REQUEST, stageSubjectFindDao.getStageItemList());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
