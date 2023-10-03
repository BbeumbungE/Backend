package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.item.subject.dto.SubjectListDto;
import com.siliconvalley.domain.notification.application.NotificationPushService;
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

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UpdateRankService {

    private final RankCachingService rankCachingService;
    private final RankingRepository postCustomRepository;
    private final SubjectFindDao subjectFindDao;
    private final NotificationPushService notificationPushService;

    @PostConstruct
    public void initialRankingUpdate(){
        updateRanking();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void updateRanking(){
        List<SubjectListDto> subjects = subjectFindDao.findAllSubjects();
        for (SubjectListDto dto : subjects){
            List<PostRankingDto> postRankingDtoList = postCustomRepository.getSubjectRankingThisWeek(dto.getSubjectId());
            log.info("랭킹 개수" + postRankingDtoList.size());
            for (PostRankingDto postRankingDto : postRankingDtoList){
                log.info(postRankingDto.getPostId() + "번 포스트");
                notificationPushService.pushNotification(postRankingDto);
            }
            rankCachingService.cachingRankToRedis(new RankingCachingDto(postRankingDtoList, dto.getSubjectName(), dto.getSubjectId()));
        }
    }
}
