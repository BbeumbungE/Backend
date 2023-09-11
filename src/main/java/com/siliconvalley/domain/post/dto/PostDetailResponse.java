package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.profile.dto.ProfileAvatarItemResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDetailResponse {
    private String canvasUrl;
    private Long authorProfileId;
    private ProfileAvatarItemResponse authorProfileAvatar;
    private List<PostEmotionTypeInfo> postEmotionTypeInfos;

    public PostDetailResponse(Post post, List<PostEmotionTypeInfo> postEmotionTypeInfos){
        this.canvasUrl = post.getCanvas().getCanvas();
        this.authorProfileId = post.getCanvas().getProfile().getId();
        this.authorProfileAvatar = new ProfileAvatarItemResponse(post.getCanvas().getProfile().getProfileItem());
        this.postEmotionTypeInfos = postEmotionTypeInfos;
    }
}
