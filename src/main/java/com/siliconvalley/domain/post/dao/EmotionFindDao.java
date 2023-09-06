package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.domain.Emotion;
import com.siliconvalley.domain.post.exception.EmotionNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

}
