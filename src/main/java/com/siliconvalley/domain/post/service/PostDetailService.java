package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.post.code.PostCode;
import com.siliconvalley.domain.post.dao.EmotionCustomRepository;
import com.siliconvalley.domain.post.dao.PostFindDao;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.post.dto.PostDetailResponse;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostDetailService {

    private final PostFindDao postFindDao;
    private final EmotionCustomRepository emotionCustomRepository;

    public Response getPostDetail(Long postId, Long profileId){
        Post post = postFindDao.findById(postId);
        return Response.of(PostCode.GET_POST_DETAIL_SUCCESS,
                new PostDetailResponse(post, emotionCustomRepository.findEmotionStatsByPostAndProfile(postId, profileId)));
    }
}
