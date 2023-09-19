package com.siliconvalley.domain.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.siliconvalley.domain.post.domain.EmotionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostEmotionTypeInfo {
    private Long emotionTypeId;
    private String emotionTypeName;
    private Long emotionCount;
    private boolean isEmoted;

    @QueryProjection
    public PostEmotionTypeInfo(EmotionType emotionType, Long emotionCount, boolean isEmoted){
        this.emotionTypeId = emotionType.getId();
        this.emotionTypeName = emotionType.getEmotionName();
        this.emotionCount = emotionCount;
        this.isEmoted = isEmoted;
    }
}
