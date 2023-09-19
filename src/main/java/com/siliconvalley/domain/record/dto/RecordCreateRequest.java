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
    private int score;

    RecordCreateRequest(
            @Valid int score
    ) {
        this.score = score;
    }

    public Record toEntity(Profile profile, Stage stage) {
        return Record.builder()
                .score(score)
                .profile(profile)
                .stage(stage)
                .build();
    }
}
