package com.siliconvalley.domain.item.item.api;

import com.siliconvalley.domain.item.item.application.AvatarItemCreateService;
import com.siliconvalley.domain.item.item.dao.AvatarItemFindDao;
import com.siliconvalley.domain.item.item.dto.AvatarItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/items/avatars")
@RequiredArgsConstructor
public class AvatarItemApi {

    private final AvatarItemCreateService avatarItemCreateService;
    private final AvatarItemFindDao avatarItemFindDao;

    @PostMapping
    public ResponseEntity createAvatarItem(@RequestBody @Valid AvatarItemCreateRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avatarItemCreateService.createAvatarItem(dto));
    }

    @GetMapping
    public ResponseEntity getAllAvatarItems(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(avatarItemFindDao.getAvatarItemListByPage(pageable));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity getAvatarItem(
            @PathVariable("itemId") Long itemId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(avatarItemFindDao.getAvatarItemById(itemId));
    }
}
