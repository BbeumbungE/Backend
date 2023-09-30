package com.siliconvalley.domain.stage.dto;

import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.stage.domain.Stage;
import com.siliconvalley.domain.item.subject.dto.SubjectResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageResponse {

    private Long id;
    private int stageNum;
    private int point;
    private int timeLimit;
    private SubjectResponse subject;

    public StageResponse(final Stage stage, final Subject subject) {
        this.id = stage.getId();
        this.stageNum = stage.getStageNum();
        this.point = stage.getPoint();
        this.timeLimit = stage.getTimeLimit();
        this.subject = new SubjectResponse(subject);
    }

    public StageResponse(final Stage stage) {
        this.id = stage.getId();
        this.stageNum = stage.getStageNum();
        this.point = stage.getPoint();
        this.timeLimit = stage.getTimeLimit();
        this.subject = null;
    }

    public StageResponse(int stageNum){
        this.stageNum = stageNum;
    }
}
