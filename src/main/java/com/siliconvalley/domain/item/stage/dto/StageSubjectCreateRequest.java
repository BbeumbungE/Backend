package com.siliconvalley.domain.item.stage.dto;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.stage.domain.Stage;
import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageSubjectCreateRequest {

    @Valid
    private int stage;

    @Valid
    private int point;

    @Valid
    private String subjectName;

    @Valid
    private String subjectImage;

    StageSubjectCreateRequest(
            @Valid int stage,
            @Valid int point,
            @Valid String subjectName,
            @Valid String subjectImage
    ) {
        this.stage = stage;
        this.point = point;
        this.subjectName = subjectName;
        this.subjectImage = subjectImage;
    }

    public Stage toStageEntity() {
        return Stage.builder()
                .stage(stage)
                .point(point)
                .build();
    }

    public Subject toSubjectEntity() {
        return Subject.builder()
                .subjectName(subjectName)
                .subjectImage(subjectImage)
                .build();
    }
}
