package com.siliconvalley.domain.item.myitem.dto;

import com.siliconvalley.domain.item.myitem.domain.MyItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyItemPostSuccessResponse {
    private Long id;
    public MyItemPostSuccessResponse(MyItem myItem) {
        this.id = myItem.getId();
    }
}
