package com.siliconvalley.domain.item.subject.dto;

import com.siliconvalley.domain.item.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectItemResponse {

    private Long id;
    private Long itemPrice;
    private SubjectResponse subjectResponse;


    public SubjectItemResponse(final Item item) {
        this.id = item.getId();
        this.itemPrice = item.getItemPrice();
        this.subjectResponse = new SubjectResponse(item.getSubject());
    }
}
