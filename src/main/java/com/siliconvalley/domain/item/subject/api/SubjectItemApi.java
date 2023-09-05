package com.siliconvalley.domain.item.subject.api;

import com.siliconvalley.domain.item.item.code.ItemCode;
import com.siliconvalley.domain.item.myitem.application.MyItemCreateService;
import com.siliconvalley.domain.item.subject.application.SketchCreateService;
import com.siliconvalley.domain.item.subject.application.SubjectCreateService;
import com.siliconvalley.domain.item.subject.code.SketchCode;
import com.siliconvalley.domain.item.subject.dao.SketchFindDao;
import com.siliconvalley.domain.item.subject.dao.SubjectItemFindDao;
import com.siliconvalley.domain.item.subject.dto.SketchCreateRequest;
import com.siliconvalley.domain.item.subject.dto.SubjectItemCreateRequest;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/items/subjects")
@RequiredArgsConstructor
public class SubjectItemApi {

    private final SubjectCreateService subjectCreateService;
    private final SubjectItemFindDao subjectItemFindDao;
    private final MyItemCreateService myItemCreateService;
    private final SketchCreateService sketchCreateService;
    private final SketchFindDao sketchFindDao;

    // 새 subject 아이템 생성 // admin 가능하게 권한 설정 필요
    @PostMapping
    public ResponseEntity createSubjectItem(@RequestBody @Valid SubjectItemCreateRequest dto) {
        Response response = Response.of(ItemCode.CREATE_SUCCESS, subjectCreateService.createSubjectItem(dto));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllSubjectItems(Pageable pageable) {
        Response response = Response.of(CommonCode.GOOD_REQUEST, subjectItemFindDao.getSubjectItemListByPage(pageable));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity getSubjectItem(@PathVariable("itemId") Long itemId) {
        Response response = Response.of(CommonCode.GOOD_REQUEST, subjectItemFindDao.getSubjectItemById(itemId));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/{subjectId}/sketches")
    public ResponseEntity addSketch(
            @PathVariable(name = "subjectId") Long subjectId,
            @RequestBody @Valid SketchCreateRequest dto) {
        Response response = Response.of(SketchCode.CREATE_SUCCESS, sketchCreateService.createSketch(subjectId, dto));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/{subjectId}/sketches")
    public ResponseEntity getAllSketches(@PathVariable(name = "subjectId") Long subjectId) {
        Response response = Response.of(CommonCode.GOOD_REQUEST, sketchFindDao.getAllsketches(subjectId));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
