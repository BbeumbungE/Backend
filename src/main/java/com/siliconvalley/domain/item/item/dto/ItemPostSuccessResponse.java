package com.siliconvalley.domain.item.item.dto;

import com.siliconvalley.domain.item.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemPostSuccessResponse {
    private Long id;

    public ItemPostSuccessResponse(Item item) {
        this.id = item.getId();
    }
}
