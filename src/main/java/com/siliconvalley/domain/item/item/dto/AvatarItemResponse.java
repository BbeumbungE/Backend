package com.siliconvalley.domain.item.item.dto;

import com.siliconvalley.domain.item.avatar.dto.AvatarResponse;
import com.siliconvalley.domain.item.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AvatarItemResponse {

    private Long id;
    private Long itemPrice;
    private boolean hasItem;
    private AvatarResponse avatarResponse;

    public AvatarItemResponse(final Item item) {
        this.id = item.getId();
        this.itemPrice = item.getItemPrice();
        this.hasItem = true;
        this.avatarResponse = new AvatarResponse(item.getAvatar());
    }

    public AvatarItemResponse(final Item item, final boolean hasItem) {
        this.id = item.getId();
        this.itemPrice = item.getItemPrice();
        this.hasItem = hasItem;
        this.avatarResponse = new AvatarResponse(item.getAvatar());
    }
}
