package com.siliconvalley.domain.item.item.api;

import com.siliconvalley.domain.item.avatar.application.AvatarCreateService;
import com.siliconvalley.domain.item.item.dao.AvatarItemFindDao;
import com.siliconvalley.domain.item.item.dto.AvatarItemCreateRequest;
import com.siliconvalley.domain.item.item.code.ItemCode;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
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

    private final AvatarCreateService avatarCreateService;
    private final AvatarItemFindDao avatarItemFindDao;

    @PostMapping
    public ResponseEntity createAvatarItem(@RequestBody @Valid AvatarItemCreateRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avatarCreateService.createAvatarItem(dto));
    }

    @GetMapping
    public ResponseEntity getAllAvatarItems(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(avatarItemFindDao.getAvatarItemListByPage(pageable));
    }
}
