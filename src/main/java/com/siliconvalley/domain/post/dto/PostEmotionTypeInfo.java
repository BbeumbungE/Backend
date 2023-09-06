package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.post.domain.EmotionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PostEmotionTypeInfo {
    private Long emotionTypeId;
    private String emotionTypeName;
    private Long emotionCount;
    private boolean isEmoted;

    public PostEmotionTypeInfo(EmotionType emotionType, Long emotionCount, boolean isEmoted){
        this.emotionTypeId = emotionType.getId();
        this.emotionTypeName = emotionType.getEmotionName();
        this.emotionCount = emotionCount;
        this.isEmoted = isEmoted;
    }
}
