package com.siliconvalley.domain.item.stage.dto;

import com.siliconvalley.domain.item.stage.domain.Stage;
import com.siliconvalley.domain.item.subject.dto.SubjectResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageSubjectResponse {

    private StageResponse stage;
    private SubjectResponse subject;

    public StageSubjectResponse(Stage stage) {
        this.stage = new StageResponse(stage);
        this.subject = new SubjectResponse(stage.getSubject());
    }
}
