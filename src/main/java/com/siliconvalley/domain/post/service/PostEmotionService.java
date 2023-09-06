package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.post.code.EmotionCode;
import com.siliconvalley.domain.post.dao.EmotionFindDao;
import com.siliconvalley.domain.post.dao.EmotionRepository;
import com.siliconvalley.domain.post.dao.EmotionTypeFindDao;
import com.siliconvalley.domain.post.dao.PostFindDao;
import com.siliconvalley.domain.post.domain.Emotion;
import com.siliconvalley.domain.post.domain.EmotionType;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.post.dto.EmotionCreatedResponse;
import com.siliconvalley.domain.post.dto.PostResponse;
import com.siliconvalley.domain.post.exception.IllegalDeleteException;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostEmotionService {

    private final EmotionFindDao emotionFindDao;
    private final PostFindDao postFindDao;
    private final EmotionRepository emotionRepository;
    private final EmotionTypeFindDao emotionTypeFindDao;

    public Response emoteToPost(Long postId, Long emotionTypeId, Profile profile){
        Post post = postFindDao.findById(postId);
        Emotion emotion = post.addEmotion(emotionTypeFindDao.findById(emotionTypeId), profile);
        return Response.of(EmotionCode.CANCEL_EMOTION_SUCCESS, new EmotionCreatedResponse(emotion));
    }

    public Response cancelEmote(Long emotionId, Long requestProfileId){
        Emotion emotion = emotionFindDao.findById(emotionId);
        if (emotion.getProfile().getId() != requestProfileId){
            throw new IllegalDeleteException("요청 ID와 감정표현 프로필 ID가 맞지않음")
        }
        emotionRepository.delete(emotion);
        return Response.of(EmotionCode.CANCEL_EMOTION_SUCCESS, null);
    }

}
