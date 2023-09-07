package com.siliconvalley.domain.item.item.api;

import com.siliconvalley.domain.item.item.dao.ItemFindDao;
import lombok.RequiredArgsConstructor;
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
        return ResponseEntity.status(HttpStatus.OK).body(itemFindDao.getItemById(itemId, category));
    }
}
