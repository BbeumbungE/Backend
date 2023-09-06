package com.siliconvalley.domain.item.item.api;

import com.siliconvalley.domain.item.item.code.ItemCode;
import com.siliconvalley.domain.item.item.dao.ItemFindDao;
import com.siliconvalley.domain.item.subject.application.SubjectCreateService;
import com.siliconvalley.domain.item.item.dao.RankSubjectFindDao;
import com.siliconvalley.domain.item.stage.dao.StageSubjectFindDao;
import com.siliconvalley.domain.item.item.dto.RankSubjectItemCreateRequest;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemApi {

    private final ItemFindDao itemFindDao;

    @GetMapping("/{itemId}")
    public ResponseEntity getItem(
            @PathVariable("itemId") Long itemId,
            @RequestParam(value = "category") String category
    ) {
        Response response = Response.of(CommonCode.GOOD_REQUEST, itemFindDao.getItemById(itemId, category));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
