package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.post.dto.RankingCachingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class RankCachingService {

    private RedisTemplate<String, Object> redisTemplate;

    public void cachingRankToRedis(RankingCachingDto rankingCachingDto){
        LocalDateTime now = getCurrentTime();
        String redisKey = generateRedisKey(now);
        redisTemplate.opsForList().rightPush(redisKey, rankingCachingDto);

        // 키의 만료 시간을 4주로 설정
        redisTemplate.expire(redisKey, 28, TimeUnit.DAYS);
    }

    private LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    private String generateRedisKey(LocalDateTime now) {
        int weekOfYear = now.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        int dayOfWeek = now.get(WeekFields.of(Locale.getDefault()).dayOfWeek());

        return "week:" + weekOfYear + ":day:" + dayOfWeek + ":rank";
    }
}
