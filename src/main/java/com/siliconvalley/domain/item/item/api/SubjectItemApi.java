package com.siliconvalley.domain.item.item.api;

import com.siliconvalley.domain.item.item.code.ItemCode;
import com.siliconvalley.domain.item.item.dao.RankSubjectFindDao;
import com.siliconvalley.domain.item.item.dto.RankSubjectItemCreateRequest;
import com.siliconvalley.domain.item.subject.application.SubjectCreateService;
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
    private final RankSubjectFindDao rankSubjectFindDao;

    /**
     * Rank Subject Management
     **/

    // 새 subject 아이템 생성 // admin 가능하게 권한 설정 필요
    @PostMapping
    public ResponseEntity createRankSubject(@RequestBody @Valid RankSubjectItemCreateRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectCreateService.createRankSubject(dto));
    }

    @GetMapping
    public ResponseEntity getAllSubjectItems(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(rankSubjectFindDao.getSubjectItemListByPage(pageable));
    }
}
