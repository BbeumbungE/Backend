package com.siliconvalley.domain.post.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siliconvalley.domain.post.domain.Emotion;
import com.siliconvalley.domain.post.dto.PostEmotionTypeInfo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public class EmotionCustomRepositoryImpl extends QuerydslRepositorySupport implements EmotionCustomRepository{

    public EmotionCustomRepositoryImpl(){
        super(Emotion.class);
    }

    @Override
    public List<PostEmotionTypeInfo> findEmotionStatsByPostId(Long postId, Long profileId) {
         QEmo  = QEmotion.emotion;

        return queryFactory
                .select(new QPostEmotionTypeInfo(
                        emotion.emotionType,
                        emotion.count(),
                        emotion.profile.id.eq(profileId).then(1).otherwise(0).sum().gt(0)
                ))
                .from(emotion)
                .where(emotion.post.id.eq(postId))
                .groupBy(emotion.emotionType)
                .fetch();
    }
}
