package com.siliconvalley.domain.post.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siliconvalley.domain.canvas.domain.QCanvas;
import com.siliconvalley.domain.post.domain.QEmotion;
import com.siliconvalley.domain.post.domain.QPost;
import com.siliconvalley.domain.post.dto.PostRankingDto;
import com.siliconvalley.domain.post.dto.QPostRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RankingRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<PostRankingDto> getSubjectRankingThisWeek(Long subjectId){
        QPost post = QPost.post;
        QEmotion emotion = QEmotion.emotion;
        QCanvas canvas = QCanvas.canvas1;

        Map<String, LocalDateTime> weekMap = getStartAndEndOfWeek();
        LocalDateTime startOfWeek = weekMap.get("startOfWeek");
        LocalDateTime endOfWeek = weekMap.get("endOfWeek");

        return jpaQueryFactory
                .select(new QPostRankingDto(canvas.profile.id, canvas.id, canvas.canvas, post.id))
                .from(emotion)
                .join(emotion.post, post)
                .join(post.canvas, canvas)
                .where(emotion.createdAt.between(startOfWeek, endOfWeek).
                        and(canvas.subject.id.eq(subjectId)))
                .groupBy(post.id, canvas.id, canvas.canvas)
                .orderBy(emotion.count().desc(), post.createdAt.asc()) // 추가된 정렬 기준
                .limit(8)
                .fetch();
    }

    private Map<String, LocalDateTime> getStartAndEndOfWeek() {
        Map<String, LocalDateTime> weekMap = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfWeek = now.with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfWeek = now.with(DayOfWeek.SUNDAY).withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        weekMap.put("startOfWeek", startOfWeek);
        weekMap.put("endOfWeek", endOfWeek);

        return weekMap;
    }

}
