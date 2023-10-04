package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.post.code.RankingCode;
import com.siliconvalley.domain.post.dto.RankingCachingDto;
import com.siliconvalley.domain.post.dto.RankingPeriodDto;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RankCachingService {

    private final RedisTemplate<String, RankingCachingDto> redisTemplate;

    public Response getRankingThisWeekBySubject(Long subjectId){
        RankingCachingDto rankingCachingDto = redisTemplate.opsForList().index(generateRedisKey(subjectId), -1);
        return Response.of(RankingCode.GET_RANKING_SUCCESS, rankingCachingDto);
    }

    public void cachingRankToRedis(RankingCachingDto rankingCachingDto){
        redisTemplate.opsForList().rightPush(generateRedisKey(rankingCachingDto.getSubjectId()), rankingCachingDto);
        // 키의 만료 시간을 4주로 설정
        redisTemplate.expire(generateRedisKey(rankingCachingDto.getSubjectId()), 1, TimeUnit.HOURS);
    }

    public String getTopPostThisWeek(Long subjectId){
        RankingCachingDto rankingCachingDto = redisTemplate.opsForList().index(generateRedisKey(subjectId), -1);
        if (rankingCachingDto == null){
            return null;
        }
        if (rankingCachingDto.getRankerList().isEmpty()){
            return null;
        }
        return rankingCachingDto.getRankerList().get(0).getCanvasUrl();
    }

    private String generateRedisKey(Long subjectId) {
        RankingPeriodDto dateDto = RankingPeriodDto.builder().build();
        return "week:" + dateDto.getWeekOfYear() + ":day:" + dateDto.getDayOfWeek() + ':' + "subject:" + subjectId + ":rank";
    }
}
