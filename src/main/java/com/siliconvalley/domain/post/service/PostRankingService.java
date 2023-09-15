package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.notification.application.NotificationPushService;
import com.siliconvalley.domain.notification.domain.NotificationType;
import com.siliconvalley.domain.post.dao.PostCustomRepository;
import com.siliconvalley.domain.post.dto.PostRankingDto;
import com.siliconvalley.domain.post.dto.RankingCachingDto;
import com.siliconvalley.domain.sse.application.SseEmitterFinder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostRankingService {

    private final RankCachingService rankCachingService;
    private final PostCustomRepository postCustomRepository;

    private final NotificationPushService notificationPushService;

    @Scheduled(cron = "0 0 * * * *")
    public void updateRanking(){
        List<PostRankingDto> postRankingDtoList = postCustomRepository.getSubjectRankingThisWeek();
        log.info("랭킹 개수" + postRankingDtoList.size());
        for (PostRankingDto postRankingDto : postRankingDtoList){
            log.info(postRankingDto.getPostId() + "번 포스트");
            notificationPushService.pushNotification(postRankingDto);
        }
        rankCachingService.cachingRankToRedis(new RankingCachingDto(postRankingDtoList));
    }
}
