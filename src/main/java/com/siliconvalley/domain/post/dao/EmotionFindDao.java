package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.domain.Emotion;
import com.siliconvalley.domain.post.exception.EmotionNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmotionFindDao {

    private final EmotionRepository emotionRepository;

    public Emotion findById(Long emotionId){
        Optional<Emotion> emotion = emotionRepository.findById(emotionId);
        emotion.orElseThrow(() -> new EmotionNotFoundException(emotionId + "번 감정표현"));
        return emotion.get();
    }

    public List<Emotion> findByPostId(Long postId){
        List<Emotion> emotions = emotionRepository.findByPostId(postId);
        return emotions;
    }

    public Emotion findByPostIdAndProfileId(Long profileId, Long postId){
        Optional<Emotion> emotion = emotionRepository.findByPostIdAndProfileId(profileId, postId);
        emotion.orElseThrow(()-> new EmotionNotFoundException("emotion"));
        return emotion.get();
    }

    public Long getEmotionCountByTypeAtPost(Long postId, Long emotionTypeId){
        return emotionRepository.countByPostIdAndEmotionTypeId(postId, emotionTypeId);
    }

    public Optional<Emotion> isPostEmotedByProfile(Long profileId, Long postId, Long emotionTypeId){
       return emotionRepository.findByPostIdAndProfileIdAndEmotionTypeId(postId, profileId, emotionTypeId);
    }

}
