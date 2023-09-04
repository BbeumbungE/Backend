package com.siliconvalley.domain.item.avatar.application;

import com.siliconvalley.domain.item.avatar.dao.AvatarRepository;
import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.avatar.dto.AvatarItemCreateRequest;
import com.siliconvalley.domain.item.item.dto.ItemPostSuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AvatarCreateService {

    private final ItemRepository itemRepository;
    private final AvatarRepository avatarRepository;

    public ItemPostSuccessResponse createAvatarItem(AvatarItemCreateRequest dto) {
        Item item = itemRepository.save(dto.toItemEntity());
        avatarRepository.save(dto.toAvatarEntity(item));
        return new ItemPostSuccessResponse(item);
    }
}
