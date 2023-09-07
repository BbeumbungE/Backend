package com.siliconvalley.domain.post.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.siliconvalley.domain.post.dto.PostRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostCustomRepository {
    private JPAQueryFactory jpaQueryFactory;

    public List<PostRankingDto> getSubjectRankingThisWeek(){

    }
}
