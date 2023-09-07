package com.siliconvalley.domain.item.avatar.application;

import com.siliconvalley.domain.item.avatar.domain.Avatar;
import com.siliconvalley.domain.item.item.code.ItemCode;
import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.AvatarItemCreateRequest;
import com.siliconvalley.domain.item.item.dto.ItemPostSuccessResponse;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AvatarCreateService {

    private final ItemRepository itemRepository;

    public Response createAvatarItem(AvatarItemCreateRequest dto) {
        Item item = dto.toItemEntity();
        Avatar avatar = dto.toAvatarEntity();

        // Item과 Avatar 객체를 연결
        item.setAvatar(avatar);

        // Item이 저장될 때 Avatar 자동 저장
        itemRepository.save(item);

        return Response.of(ItemCode.CREATE_SUCCESS,new ItemPostSuccessResponse(item));
    }
}
