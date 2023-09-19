package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RankingCachingDto {
    private String time;
    private Long subjectId;
    private String subjectName;
    private List<PostRankingDto> rankerList;

    public RankingCachingDto(List<PostRankingDto> dto, Subject subject){
        this.time = "Update Time : " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
        this.rankerList = dto;
        this.subjectId = subject.getId();
        this.subjectName = subject.getSubjectName();
    }

}
