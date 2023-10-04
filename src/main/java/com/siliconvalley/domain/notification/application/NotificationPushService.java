package com.siliconvalley.domain.notification.application;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.notification.dao.NotificationRepository;
import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.domain.notification.domain.NotificationType;
import com.siliconvalley.domain.notification.dto.NotificationResponse;
import com.siliconvalley.domain.post.dto.PostRankingDto;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.sse.application.SseEmitterFinder;
import com.siliconvalley.domain.sse.application.SseEmitterSender;
import com.siliconvalley.domain.sse.repository.EventCashRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NotificationPushService {

    private final ProfileFindDao profileFindDao;

    private final SseEmitterFinder sseEmitterFinder;
    private final SseEmitterSender sseEmitterSender;

    private final EventCashRepository eventCashRepository;
    private final NotificationRepository notificationRepository;

    public void pushNotification(Item item, NotificationType type) {
        Map<Long, SseEmitter> sseEmitterMap = sseEmitterFinder.findAll();

        sseEmitterMap.entrySet().stream().forEach(sseEmitter -> {
            Long profileId = sseEmitter.getKey();
            Optional<Profile> profileOptional = profileFindDao.findOptionalById(profileId);
            if (profileOptional.isPresent()) {
                Notification notification = Notification.toItemNotification(profileOptional.get(), item, type);
                notificationRepository.save(notification);

                NotificationResponse notificationResponse = new NotificationResponse(notification);
                String id = profileId + "_"+ System.currentTimeMillis();

                sseEmitterSender.send(sseEmitter.getValue(), id, notificationResponse ,profileId, "message"); // 알림 전송
                log.info(profileId + "번 프로필로 알림을 전송했습니다.");
                eventCashRepository.save(id, notificationResponse); // 미전송 알림 저장용
            }
        });
    }

    public void pushNotification(PostRankingDto postRankingDto) {
        Long profileId = postRankingDto.getProfileId();
        SseEmitter sseEmitter = sseEmitterFinder.findByProfileId(profileId);

        Profile profile = profileFindDao.findById(profileId);

        Notification notification = Notification.toRankingNotification(profile, NotificationType.RANKING);
        notificationRepository.save(notification);

        NotificationResponse notificationResponse = new NotificationResponse(notification);
        String id = profileId + "_"+ System.currentTimeMillis();

        if (sseEmitter != null) sseEmitterSender.send(sseEmitter, id, notificationResponse ,profileId, "message"); // 알림 전송

        eventCashRepository.save(id, notificationResponse); // 미전송 알림 저장용
    }
}
