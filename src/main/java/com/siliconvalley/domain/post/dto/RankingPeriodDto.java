package com.siliconvalley.domain.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;

@Getter
public class RankingPeriodDto {
    int weekOfYear;
    int dayOfWeek;

    @Builder
    public RankingPeriodDto(){
        LocalDateTime now = LocalDateTime.now();
        this.weekOfYear = now.get(WeekFields.ISO.weekOfWeekBasedYear());
        this.dayOfWeek = now.get(WeekFields.ISO.dayOfWeek());
    }
}
