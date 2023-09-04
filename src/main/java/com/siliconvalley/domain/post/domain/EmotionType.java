package com.siliconvalley.domain.post.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "emotion_type")
@Entity
@Slf4j
public class EmotionType {
    @Id
    @GeneratedValue
    private Long emotionTypeId;

    @Column(name = "emotion_name")
    private String emotion_name;

}
