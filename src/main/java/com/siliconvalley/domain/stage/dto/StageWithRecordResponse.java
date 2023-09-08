package com.siliconvalley.domain.stage.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.siliconvalley.domain.item.subject.dto.SubjectResponse;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.dto.RecordResponse;
import com.siliconvalley.domain.stage.domain.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageWithRecordResponse {

    private Long id;
    private int stageNum;
    private int point;
    private SubjectResponse subject;
    private RecordResponse record;

    public StageWithRecordResponse(final Stage stage, final Record record) {
        this.id = stage.getId();
        this.stageNum = stage.getStageNum();
        this.point = stage.getPoint();
        this.subject = new SubjectResponse(stage.getSubject());
        this.record = new RecordResponse(record);
    }

    public StageWithRecordResponse(final Stage stage) {
        this.id = stage.getId();
        this.stageNum = stage.getStageNum();
        this.point = stage.getPoint();
        this.subject = new SubjectResponse(stage.getSubject());
        this.record = null;
    }
}
