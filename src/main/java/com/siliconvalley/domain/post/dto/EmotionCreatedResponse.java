package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.post.domain.Emotion;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmotionCreatedResponse {
    private Long emotionId;
    private Long postId;
    public EmotionCreatedResponse(Emotion emotion){
        this.emotionId = emotion.getId();
        this.postId = emotion.getPost().getId();
    }
}
