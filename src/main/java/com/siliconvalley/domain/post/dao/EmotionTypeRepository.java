package com.siliconvalley.domain.post.dao;

import com.siliconvalley.domain.post.domain.EmotionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmotionTypeRepository extends JpaRepository<EmotionType, Long> {
}
