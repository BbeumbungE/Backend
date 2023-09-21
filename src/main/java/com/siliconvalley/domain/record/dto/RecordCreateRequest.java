package com.siliconvalley.domain.record.dto;

import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.stage.domain.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordCreateRequest {

    @Valid
    private Long canvasId;

    RecordCreateRequest(
            @Valid Long canvasId
    ) {
        this.canvasId = canvasId;
    }
}
