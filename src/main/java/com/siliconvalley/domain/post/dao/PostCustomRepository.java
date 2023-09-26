package com.siliconvalley.domain.post.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siliconvalley.domain.canvas.domain.QCanvas;
import com.siliconvalley.domain.post.domain.QPost;

import com.siliconvalley.domain.post.dto.PostListResponse;

import com.siliconvalley.domain.post.dto.QPostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Page<PostListResponse> findBySubjectId(Long subjectId, Pageable pageable) {
        QPost qPost = QPost.post;
        QCanvas qCanvas = QCanvas.canvas1;

        List<PostListResponse> results = jpaQueryFactory
                .select(new QPostListResponse(qPost.id, qCanvas.canvas))
                .from(qPost)
                .leftJoin(qPost.canvas, qCanvas)
                .where(qCanvas.subject.id.eq(subjectId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(qPost)
                .leftJoin(qPost.canvas, qCanvas)
                .where(qCanvas.subject.id.eq(subjectId))
                .fetchCount();

        return new PageImpl<>(results, pageable, total);
    }

}
