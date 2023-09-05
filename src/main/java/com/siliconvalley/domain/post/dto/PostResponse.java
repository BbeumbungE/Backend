package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.post.domain.Emotion;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponse {
    private String canvasUrl;
    private Profile profile;
    private int emotionCount;

    public PostResponse(Profile profile, Canvas canvas){

    }

}
