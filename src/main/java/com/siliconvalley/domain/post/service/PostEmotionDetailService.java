package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.post.dao.EmotionFindDao;
import com.siliconvalley.domain.post.dao.PostFindDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostEmotionDetailService {

    private final PostFindDao postFindDao;
    private final EmotionFindDao emotionFindDao;
    public Long updateEmotion(Long postId, Long emotionTypeId){
        return emotionFindDao.getEmotionCountByTypeAtPost(postId, emotionTypeId);
    }

}
