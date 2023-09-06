package com.siliconvalley.domain.item.item.dto;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RankSubjectItemCreateRequest {

    @Valid
    private Long itemPrice;

    @Valid
    private String subjectName;

    @Valid
    private String subjectImage;

    RankSubjectItemCreateRequest(
            @Valid Long itemPrice,
            @Valid String subjectName,
            @Valid String subjectImage
    ) {
        this.itemPrice = itemPrice;
        this.subjectName = subjectName;
        this.subjectImage = subjectImage;
    }

    public Item toItemEntity() {
        return Item.builder()
                .itemPrice(itemPrice)
                .build();
    }

    public Subject toSubjectEntity() {
        return Subject.builder()
                .subjectName(subjectName)
                .subjectImage(subjectImage)
                .build();
    }
}
