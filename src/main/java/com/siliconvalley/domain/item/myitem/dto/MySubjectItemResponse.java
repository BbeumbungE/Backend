package com.siliconvalley.domain.item.myitem.dto;

import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.item.item.dto.RankSubjectItemResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MySubjectItemResponse {

    private Long id;
    private String itemType;
    private RankSubjectItemResponse item;

    public MySubjectItemResponse(MyItem myItem) {
        this.id = myItem.getId();
        this.itemType = myItem.getItemType();
        this.item = new RankSubjectItemResponse(myItem.getItem());
    }
}
