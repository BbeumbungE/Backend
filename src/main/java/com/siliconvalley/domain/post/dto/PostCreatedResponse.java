package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCreatedResponse {
    private Long postId;

    public PostCreatedResponse(final Post post) {
        this.postId = post.getId();
    }

}
