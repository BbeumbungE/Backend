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
        Response response = Response.of(ItemCode.CREATE_SUCCESS, avatarCreateService.createAvatarItem(dto));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllAvatarItems(Pageable pageable) {
        Response response = Response.of(CommonCode.GOOD_REQUEST, avatarItemFindDao.getAvatarItemListByPage(pageable));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
