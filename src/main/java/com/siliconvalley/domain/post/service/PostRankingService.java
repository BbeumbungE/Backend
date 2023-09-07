package com.siliconvalley.domain.post.service;

import com.siliconvalley.domain.post.dao.PostCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostRankingService {

    private final RankCachingService rankCachingService;
    private final PostCustomRepository postCustomRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void updateRanking(){

    }

}
