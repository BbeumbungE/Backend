package com.siliconvalley.domain.post.dto;

import lombok.Getter;

@Getter
public class EmotionCreateRequest {
    private Long emotionTypeId;

    public EmotionCreateRequest(Long emotionTypeId){
        this.emotionTypeId = emotionTypeId;
    }
}
