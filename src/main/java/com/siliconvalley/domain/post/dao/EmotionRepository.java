package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    List<Emotion> findByPostId(Long postId);
}
