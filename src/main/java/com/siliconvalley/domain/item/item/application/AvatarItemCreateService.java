package com.siliconvalley.domain.item.item.application;

import com.siliconvalley.domain.item.avatar.domain.Avatar;
import com.siliconvalley.domain.item.item.code.ItemCode;
import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.ItemPostSuccessResponse;
import com.siliconvalley.domain.notification.application.NotificationPushService;
import com.siliconvalley.domain.notification.domain.NotificationType;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AvatarItemCreateService {

    private final ItemRepository itemRepository;
    private final NotificationPushService notificationPushService;

    public Response createAvatarItem(Long itemPrice, String avatarName, String imgUrl) {

        Item item = Item.toEntity(itemPrice);

        // Item과 Avatar 빌드 및 연관관계 매핑
        item.addAvatar(Avatar.toEntity(avatarName, item, imgUrl));

        // Item이 저장될 때 Avatar 자동 저장
        itemRepository.save(item);

        // 새 아이템 출시 알림
        notificationPushService.pushNotification(item, NotificationType.NEW_AVATAR);

        return Response.of(ItemCode.CREATE_SUCCESS,new ItemPostSuccessResponse(item));
    }
}
