package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.post.domain.Emotion;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.dto.ProfileResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.CountDownLatch;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponse {
    private String canvasUrl;
    private ProfileResponse profile;
    private int emotionCount;

    public PostResponse(Post post){
        this.canvasUrl = post.getCanvas().getCanvas();
        this.profile = new ProfileResponse(post.getProfile());
        this.emotionCount = post.getEmotions().size();
    }

}
