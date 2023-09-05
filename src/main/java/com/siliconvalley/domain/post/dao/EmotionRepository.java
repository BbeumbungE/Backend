package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
