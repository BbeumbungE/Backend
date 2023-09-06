package com.siliconvalley.domain.item.stage.dto;

import com.siliconvalley.domain.item.stage.domain.Stage;
import com.siliconvalley.domain.item.subject.dto.SubjectResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StageSubjectResponseList {

    private List<SubjectResponse> stageItemList;

    public StageSubjectResponseList(List<Stage> stageList) {
        this.stageItemList = stageList.stream()
                .map(stage -> new SubjectResponse(stage.getSubject()))
                .collect(Collectors.toList());
    }
}
