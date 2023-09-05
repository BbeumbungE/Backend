package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostListResponse {
    private Long postId;
    private String canvasUrl;

    public PostListResponse(Post post){
        this.postId = post.getId();
        this.canvasUrl = post.getCanvas().getCanvas();
    }

}
