package com.siliconvalley.domain.stage.dto;

import com.siliconvalley.domain.item.item.dto.SubjectItemResponse;
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
    private int timeLimit;
    private SubjectResponse subjectItem;
    private RecordResponse record;

    public StageWithRecordResponse(final Stage stage, final Record record) {
        this.id = stage.getId();
        this.stageNum = stage.getStageNum();
        this.timeLimit = stage.getTimeLimit();
        this.subjectItem = new SubjectResponse(stage.getSubject());
        this.record = new RecordResponse(record);
    }

    public StageWithRecordResponse(final Stage stage) {
        this.id = stage.getId();
        this.stageNum = stage.getStageNum();
        this.timeLimit = stage.getTimeLimit();
        this.subjectItem = new SubjectResponse(stage.getSubject());
        this.record = null;
    }

    public StageWithRecordResponse(int stageNum){
        this.stageNum = stageNum;
        this.subjectItem = null;
        this.record = null;
    }
}
