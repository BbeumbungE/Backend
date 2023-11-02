package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.notification.application.NotificationPushService;
import com.siliconvalley.domain.point.application.PointManagementService;
import com.siliconvalley.domain.post.dao.RankingRepository;
import com.siliconvalley.domain.post.dto.PostRankingDto;
import com.siliconvalley.domain.post.dto.RankingCachingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UpdateRankService {

    private final RankingRepository postCustomRepository;
    private final SubjectFindDao subjectFindDao;
    private final NotificationPushService notificationPushService;
    private final PointManagementService pointManagementService;
    private final RankCachingService rankCachingService;
    private static final int BASIC_AWARD_POINT = 80;
    private static final int RANK_DIFFERENCE_POINT = 10;
    private static final String UPDATE_POINT_SCHEDULE = "0 59 23 ? * SUN";
    private static final String UPDATE_RANKING_SCHEDULE = "0 0 * * * *";

    @PostConstruct
    public void initialRankingUpdate(){
        updateRanking();
    }

    @Scheduled(cron = UPDATE_RANKING_SCHEDULE)
    public void updateRanking(){
        subjectFindDao.findAllSubjects().stream().forEach(dto -> {
            List<PostRankingDto> postRankingDtoList = postCustomRepository.getSubjectRankingThisWeek(dto.getSubjectId());
            postRankingDtoList.forEach(notificationPushService::pushNotification);
            rankCachingService.cachingRankToRedis(RankingCachingDto.of(postRankingDtoList, dto.getSubjectName(), dto.getSubjectId()));
        });

    }

    @Scheduled(cron = UPDATE_POINT_SCHEDULE)
    public void distributePoints() {
        List<PostRankingDto> postRankingDtoList = subjectFindDao.findAllSubjects().stream()
                .flatMap(subjectListDto -> postCustomRepository.getSubjectRankingThisWeek(subjectListDto.getSubjectId()).stream())
                .collect(Collectors.toList());

        long award = BASIC_AWARD_POINT;
        for (PostRankingDto postRankingDto : postRankingDtoList) {
            pointManagementService.updatePoint(postRankingDto.getProfileId(), award);
            notificationPushService.pushNotification(postRankingDto.getProfileId(), award);
            award -= RANK_DIFFERENCE_POINT;
        }
    }
}
