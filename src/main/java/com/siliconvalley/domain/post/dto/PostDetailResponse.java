package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.post.domain.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDetailResponse {
    private String canvasUrl;
    private Long authorProfileId;
    private String authorProfileImage;
    private List<PostEmotionTypeInfo> postEmotionTypeInfos;

    public PostDetailResponse(Post post, List<PostEmotionTypeInfo> postEmotionTypeInfos){
        this.canvasUrl = post.getCanvas().getCanvas();
        this.authorProfileId = post.getCanvas().getProfile().getId();
        this.authorProfileImage = post.getCanvas().getProfile().getProfileImage();
        this.postEmotionTypeInfos = postEmotionTypeInfos;
    }
}
