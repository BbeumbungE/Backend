package com.siliconvalley.domain.item.subject.api;

import com.siliconvalley.domain.item.sketch.application.SketchCreateService;
import com.siliconvalley.domain.item.sketch.code.SketchCode;
import com.siliconvalley.domain.item.sketch.dao.SketchFindDao;
import com.siliconvalley.domain.item.sketch.dto.SketchCreateRequest;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectApi {

    private final SketchCreateService sketchCreateService;
    private final SketchFindDao sketchFindDao;


    /**
     * Sketch Of Subject Management
     **/

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
