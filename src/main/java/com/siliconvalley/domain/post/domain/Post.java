package com.siliconvalley.domain.post.domain;

import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "canvas_id")
    private Canvas canvas;

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Emotion> emotions = new ArrayList<>();

    @Builder
    public Post(Canvas canvas) {
        this.canvas = canvas;
    }

    public Emotion buildEmotion(EmotionType emotionType, Profile profile){
        return Emotion.builder()
                .post(this)
                .profile(profile)
                .emotionType(emotionType)
                .build();
    }

    public Emotion addEmotion(EmotionType emotionType, Profile profile){
        Emotion emotion = buildEmotion(emotionType, profile);
        this.emotions.add(emotion);
        return emotion;
    }

}
