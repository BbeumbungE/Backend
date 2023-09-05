package com.siliconvalley.domain.item.subject.api;

import com.siliconvalley.domain.item.myitem.application.MyItemCreateService;
import com.siliconvalley.domain.item.subject.application.SketchCreateService;
import com.siliconvalley.domain.item.subject.application.SubjectCreateService;
import com.siliconvalley.domain.item.subject.dao.SketchFindDao;
import com.siliconvalley.domain.item.subject.dao.SubjectItemFindDao;
import com.siliconvalley.domain.item.subject.dto.SketchCreateRequest;
import com.siliconvalley.domain.item.subject.dto.SubjectItemCreateRequest;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public Response createSubjectItem(@RequestBody @Valid SubjectItemCreateRequest dto) {
        return Response.of(CommonCode.SUCCESS_CREATE, subjectCreateService.createSubjectItem(dto));
    }

    @GetMapping
    public Response getAllSubjectItems(Pageable pageable) {
        return Response.of(CommonCode.GOOD_REQUEST, subjectItemFindDao.getSubjectItemListByPage(pageable));
    }

    @GetMapping("/{itemId}")
    public Response getSubjectItem(@PathVariable("itemId") Long itemId) {
        return Response.of(CommonCode.GOOD_REQUEST, subjectItemFindDao.getSubjectItemById(itemId));
    }

    @PostMapping("/{subjectId}/sketches")
    public Response addSketch(
            @PathVariable(name = "subjectId") Long subjectId,
            @RequestBody @Valid SketchCreateRequest dto) {
        return Response.of(CommonCode.SUCCESS_CREATE, sketchCreateService.createSketch(subjectId, dto));
    }

    @GetMapping("/{subjectId}/sketches")
    public Response getAllSketches(@PathVariable(name = "subjectId") Long subjectId) {
        return Response.of(CommonCode.GOOD_REQUEST, sketchFindDao.getAllsketches(subjectId));
    }
}
