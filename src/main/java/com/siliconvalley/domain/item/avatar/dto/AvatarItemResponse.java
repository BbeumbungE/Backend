package com.siliconvalley.domain.item.avatar.dto;

import com.siliconvalley.domain.item.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AvatarItemResponse {

    private Long id;
    private Long itemPrice;
    private AvatarResponse avatarResponse;

    public AvatarItemResponse(final Item item) {
        this.id = item.getId();
        this.itemPrice = item.getItemPrice();
        this.avatarResponse = new AvatarResponse(item.getAvatar());
    }
}
