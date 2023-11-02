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
    private Long subjectId;
    private String subjectName;
    private List<PostRankingDto> rankerList;

    public static RankingCachingDto of(List<PostRankingDto> dto, String subjectName, Long subjectId){
        return new RankingCachingDto(dto, subjectName, subjectId);
    }

    private RankingCachingDto(List<PostRankingDto> dto, String subjectName, Long subjectId){
        this.time = "Update Time : " + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
        this.rankerList = dto;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

}
