package com.siliconvalley.domain.item.item.dto;

import com.siliconvalley.domain.item.avatar.domain.Avatar;
import com.siliconvalley.domain.item.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AvatarItemCreateRequest {

    @Valid
    private Long itemPrice;

    @Valid
    private String avatarName;

    AvatarItemCreateRequest(
            @Valid Long itemPrice,
            @Valid String avatarName
    ) {
        this.itemPrice = itemPrice;
        this.avatarName = avatarName;
    }

    public Item getItem() {
        return Item.builder()
                .itemPrice(itemPrice)
                .build();
    }

    public Avatar getAvatar(Item item, String imgUrl) {
        return Avatar.builder()
                .avatarName(avatarName)
                .avatarImage(imgUrl)
                .item(item)
                .build();
    }
}
