package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmotionRepository extends JpaRepository<Emotion, Long>{
    List<Emotion> findByPostId(Long postId);
    Optional<Emotion> findByPostIdAndProfileId(Long postId, Long profileId);
    Long countByPostIdAndEmotionTypeId(Long postId, Long emotionTypeId);
    Optional<Emotion> findByPostIdAndProfileIdAndEmotionTypeId(Long postId, Long profileId, Long emotionTypeId);
}
