package com.siliconvalley.domain.item.myitem.dto;

import com.siliconvalley.domain.item.item.dto.AvatarItemResponse;
import com.siliconvalley.domain.item.myitem.domain.MyItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyAvatarItemResponse {

    private Long id;
    private String itemType;
    private AvatarItemResponse item;

    public MyAvatarItemResponse(MyItem myItem) {
        this.id = myItem.getId();
        this.itemType = myItem.getItemType();
        this.item = new AvatarItemResponse(myItem.getItem());
    }
}
