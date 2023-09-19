package com.siliconvalley.domain.post.dao;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siliconvalley.domain.post.domain.QEmotion;
import com.siliconvalley.domain.post.domain.QEmotionType;
import com.siliconvalley.domain.post.dto.PostEmotionTypeInfo;
import com.siliconvalley.domain.post.dto.QPostEmotionTypeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmotionCustomRepository {
    private final JPAQueryFactory  jpaQueryFactory;

    public List<PostEmotionTypeInfo> findEmotionStatsByPostAndProfile(Long postId, Long profileId) {
        QEmotion emotion = QEmotion.emotion;
        QEmotionType emotionType = QEmotionType.emotionType;

        return jpaQueryFactory
                .select(new QPostEmotionTypeInfo(
                        emotionType,
                        emotion.count(),
                        JPAExpressions
                                .selectOne()
                                .from(emotion)
                                .where(emotion.post.id.eq(postId)
                                        .and(emotion.profile.id.eq(profileId))
                                        .and(emotion.emotionType.eq(emotionType)))
                                .exists()
                ))
                .from(emotion)
                .join(emotion.emotionType, emotionType)
                .where(emotion.post.id.eq(postId))
                .groupBy(emotionType)
                .fetch();
    }
}
