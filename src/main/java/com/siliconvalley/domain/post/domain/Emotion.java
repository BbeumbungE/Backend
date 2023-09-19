package com.siliconvalley.domain.post.domain;

import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emotion")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "profile_id", nullable = false)
    @ManyToOne
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "emotion_type_id", nullable = false)
    private EmotionType emotionType;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Emotion(Profile profile, EmotionType emotionType, Post post){
        this.emotionType = emotionType;
        this.profile = profile;
        this.post = post;
    }

    public void updateEmotion(EmotionType emotionType){
        this.emotionType = emotionType;
    }

}
