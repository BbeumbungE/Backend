package com.siliconvalley.domain.stage.dto;

import com.siliconvalley.domain.item.item.dto.SubjectItemResponse;
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
    private SubjectItemResponse subjectItem;
    private RecordResponse record;

    public StageWithRecordResponse(final Stage stage, final Record record, final boolean hasItem) {
        this.id = stage.getId();
        this.stageNum = stage.getStageNum();
        this.subjectItem = new SubjectItemResponse(stage.getSubject().getItem(), hasItem);
        this.record = new RecordResponse(record);
    }

    public StageWithRecordResponse(final Stage stage, final boolean hasItem) {
        this.id = stage.getId();
        this.stageNum = stage.getStageNum();
        this.subjectItem = new SubjectItemResponse(stage.getSubject().getItem(), hasItem);
        this.record = null;
    }
}
