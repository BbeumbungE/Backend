package com.siliconvalley.domain.item.item.dto;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.subject.dto.SubjectResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RankSubjectItemResponse {

    private Long id;
    private Long itemPrice;
    private SubjectResponse subject;

    public RankSubjectItemResponse(final Item item) {
        this.id = item.getId();
        this.itemPrice = item.getItemPrice();
        this.subject = new SubjectResponse(item.getSubject());
    }
}
