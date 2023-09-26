package com.siliconvalley.domain.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.siliconvalley.domain.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostListResponse {
    private Long postId;
    private String canvasUrl;

    @QueryProjection
    public PostListResponse(Long postId, String canvasUrl){
        this.postId = postId;
        this.canvasUrl = canvasUrl;
    }

}
