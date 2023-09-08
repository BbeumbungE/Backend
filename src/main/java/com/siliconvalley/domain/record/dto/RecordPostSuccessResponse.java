package com.siliconvalley.domain.record.dto;

import com.siliconvalley.domain.record.domain.Record;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordPostSuccessResponse {

    private Long id;

    public RecordPostSuccessResponse(Record record) {
        this.id = record.getId();
    }
}
