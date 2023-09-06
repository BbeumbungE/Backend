package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.post.code.PostCode;
import com.siliconvalley.domain.post.dao.EmotionFindDao;
import com.siliconvalley.domain.post.dao.EmotionTypeFindDao;
import com.siliconvalley.domain.post.dao.PostFindDao;
import com.siliconvalley.domain.post.domain.EmotionType;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.post.dto.EmotionDetailUpdateDto;
import com.siliconvalley.domain.post.dto.PostDetailResponse;
import com.siliconvalley.domain.post.dto.PostEmotionTypeInfo;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostInformationService {

    private final EmotionFindDao emotionFindDao;
    private final EmotionTypeFindDao emotionTypeFindDao;
    private final PostFindDao postFindDao;

    public Response getPostInfo(Long postId, Long profileId){
        Post post = postFindDao.findById(postId);
        return Response.of(PostCode.GET_POST_DETAIL_SUCCESS, new PostDetailResponse(post));
    }
}
