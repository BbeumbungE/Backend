package com.siliconvalley.domain.item.avatar.api;

import com.siliconvalley.domain.item.avatar.application.AvatarCreateService;
import com.siliconvalley.domain.item.avatar.dao.AvatarItemFindDao;
import com.siliconvalley.domain.item.avatar.dto.AvatarItemCreateRequest;
import com.siliconvalley.domain.item.myitem.application.MyItemCreateService;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/items/avatars")
@RequiredArgsConstructor
public class AvatarItemApi {

    private final AvatarCreateService avatarCreateService;
    private final AvatarItemFindDao avatarItemFindDao;
    private final MyItemCreateService myItemCreateService;

    // 새 avatar 아이템 생성 // admin 가능하게 권한 설정 필요
    @PostMapping
    public Response createAvatarItem(@RequestBody @Valid AvatarItemCreateRequest dto) {
        return Response.of(CommonCode.SUCCESS_CREATE, avatarCreateService.createAvatarItem(dto));
    }

    @GetMapping
    public Response getAllAvatarItems(Pageable pageable) {
        return Response.of(CommonCode.GOOD_REQUEST, avatarItemFindDao.getAvatarItemListByPage(pageable));
    }

    @GetMapping("/{itemId}")
    public Response getAvatarItem(@PathVariable("itemId") Long itemId) {
        return Response.of(CommonCode.GOOD_REQUEST, avatarItemFindDao.getAvatarItemById(itemId));
    }

    @PostMapping("/{itemId}/{profileId}")
    public Response purchaseAvatarItem(
            @PathVariable Long itemId,
            @PathVariable Long profileId
    ) {
        return Response.of(CommonCode.SUCCESS_CREATE, myItemCreateService.createMyItem(profileId, itemId, "avatar"));
    }
}
