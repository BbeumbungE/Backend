package com.siliconvalley.domain.item.item.dto;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.subject.dto.SubjectResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectItemResponse {

    private Long id;
    private Long itemPrice;
    private boolean hasItem;
    private SubjectResponse subject;

    public SubjectItemResponse(final Item item) {
        this.id = item.getId();
        this.itemPrice = item.getItemPrice();
        this.hasItem = true;
        this.subject = new SubjectResponse(item.getSubject());
    }

    public SubjectItemResponse(final Item item, final boolean hasItem) {
        this.id = item.getId();
        this.itemPrice = item.getItemPrice();
        this.hasItem = hasItem;
        this.subject = new SubjectResponse(item.getSubject());
    }
}
