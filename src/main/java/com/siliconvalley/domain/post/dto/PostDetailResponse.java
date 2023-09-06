package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.profile.dto.ProfileResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDetailResponse {
    private String canvasUrl;
    private ProfileResponse authorProfile;
    private List<PostEmotionTypeInfo> postEmotionTypeInfos;

    public PostDetailResponse(Post post){
        this.canvasUrl = post.getCanvas().getCanvas();
        this.authorProfile = new ProfileResponse(post.getProfile());
    }

    public void addPostEmotionInfo(PostEmotionTypeInfo postEmotionTypeInfo){
        this.postEmotionTypeInfos.add(postEmotionTypeInfo);
    }
}
