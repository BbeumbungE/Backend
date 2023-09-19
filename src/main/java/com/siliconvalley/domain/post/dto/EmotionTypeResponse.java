package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.post.domain.EmotionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmotionTypeResponse {
    private Long emotionTypeId;
    private String emotionTypeName;

    public EmotionTypeResponse(EmotionType emotionType){
        this.emotionTypeId = emotionType.getId();
        this.emotionTypeName = emotionType.getEmotionName();
    }

}
