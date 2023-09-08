package com.siliconvalley.domain.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRankingDto {

    private Long canvasId;
    private Long postId;
    private String canvasUrl;

    @QueryProjection
    public PostRankingDto(Long canvasId, String canvasUrl, Long postId){
        this.canvasId = canvasId;
        this.canvasUrl = canvasUrl;
        this.postId = postId;
    }

}
