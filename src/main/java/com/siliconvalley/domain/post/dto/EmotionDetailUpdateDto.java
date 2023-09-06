package com.siliconvalley.domain.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmotionDetailUpdateDto {
    private Long emotionCount;
    private boolean isEmoted;

    public EmotionDetailUpdateDto(Long emotionCount, boolean isEmoted){
        this.emotionCount = emotionCount;
        this.isEmoted = isEmoted;
    }
}
