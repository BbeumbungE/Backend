package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.dto.PostEmotionTypeInfo;

import java.util.List;

public interface EmotionCustomRepository {
    List<PostEmotionTypeInfo> findEmotionStatsByPostId(Long postId, Long profileId);
}