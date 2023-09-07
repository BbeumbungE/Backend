package com.siliconvalley.domain.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.siliconvalley.domain.canvas.domain.Canvas;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRankingDto {

    private Long canvasId;
    private String canvasUrl;

    @QueryProjection
    public PostRankingDto(Long canvasId, String canvasUrl){
        this.canvasId = canvasId;
        this.canvasUrl = canvasUrl;
    }

}
