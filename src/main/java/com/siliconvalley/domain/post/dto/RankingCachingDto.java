package com.siliconvalley.domain.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RankingCachingDto {
    private String time;
    private String subjectName;
    private List<PostRankingDto> rankerList;

    public RankingCachingDto(List<PostRankingDto> dto, String subjectName){
        this.time = "Update Time : " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
        this.rankerList = dto;
        this.subjectName = subjectName;
    }

}
