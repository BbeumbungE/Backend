package com.siliconvalley.domain.item.item.api;

import com.siliconvalley.domain.item.item.dao.SubjectItemFindDao;
import com.siliconvalley.domain.item.item.dto.SubjectItemCreateRequest;
import com.siliconvalley.domain.item.item.application.SubjectItemCreateService;
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

    private final SubjectItemCreateService subjectItemCreateService;
    private final SubjectItemFindDao subjectItemFindDao;

    /**
     * Rank Subject Management
     **/

    // 새 subject 아이템 생성 // admin 가능하게 권한 설정 필요
    @PostMapping
    public ResponseEntity createRankSubject(@RequestBody @Valid SubjectItemCreateRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectItemCreateService.createSubjectItem(dto));
    }

    @GetMapping
    public ResponseEntity getAllSubjectItems(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectItemFindDao.getSubjectItemListByPage(pageable));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity getAvatarItem(
            @PathVariable("itemId") Long itemId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectItemFindDao.getSubjectItemById(itemId));
    }
}
