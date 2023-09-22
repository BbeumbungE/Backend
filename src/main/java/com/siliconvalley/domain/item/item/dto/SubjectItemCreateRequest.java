package com.siliconvalley.domain.item.item.dto;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectItemCreateRequest {

    @Valid
    private Long itemPrice;

    @Valid
    private String subjectName;

    SubjectItemCreateRequest(
            @Valid Long itemPrice,
            @Valid String subjectName
    ) {
        this.itemPrice = itemPrice;
        this.subjectName = subjectName;
    }

    public Item getItem() {
        return Item.builder()
                .itemPrice(itemPrice)
                .build();
    }

    public Subject getSubject(Item item, String subjectImgUrl, String sketchImgUrl) {
        return Subject.builder()
                .subjectName(subjectName)
                .subjectImage(subjectImgUrl)
                .item(item)
                .build();
    }
}
