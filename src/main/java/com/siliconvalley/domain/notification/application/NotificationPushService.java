package com.siliconvalley.domain.notification.application;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.notification.dao.NotificationRepository;
import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.domain.notification.domain.NotificationType;
import com.siliconvalley.domain.notification.dto.NotificationResponse;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.sse.application.SseEmitterFinder;
import com.siliconvalley.domain.sse.application.SseEmitterSender;
import com.siliconvalley.domain.sse.repository.EventCashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Transactional
public class NotificationPushService {

    private final ProfileFindDao profileFindDao;

    private final SseEmitterFinder sseEmitterFinder;
    private final SseEmitterSender sseEmitterSender;

    private final EventCashRepository eventCashRepository;
    private final NotificationRepository notificationRepository;

    public void pushNotification(Item item, NotificationType type) {
        Map<Long, SseEmitter> sseEmitterMap = sseEmitterFinder.findALl();

        sseEmitterMap.entrySet().stream().forEach(sseEmitter -> {
            Long profileId = sseEmitter.getKey();
            Profile profile = profileFindDao.findById(profileId);
            Notification notification = Notification.toItemNotification(profile, item, type);
            notificationRepository.save(notification);

            NotificationResponse notificationResponse = new NotificationResponse(notification);
            String id = profileId + "_"+ System.currentTimeMillis();

            sseEmitterSender.send(sseEmitter.getValue(), id, notificationResponse ,profileId); // 알림 전송
            eventCashRepository.save(id, notificationResponse); // 미전송 알림 저장용
        });
    }

    public void pushNotification(NotificationType type) {
        Map<Long, SseEmitter> sseEmitterMap = sseEmitterFinder.findALl();

        sseEmitterMap.entrySet().stream().forEach(sseEmitter -> {
            Long profileId = sseEmitter.getKey();
            Profile profile = profileFindDao.findById(profileId);
            Notification notification = Notification.toRankingNotification(profile, type);
            notificationRepository.save(notification);

            NotificationResponse notificationResponse = new NotificationResponse(notification);
            String id = profileId + "_"+ System.currentTimeMillis();

            sseEmitterSender.send(sseEmitter.getValue(), id, notificationResponse ,profileId); // 알림 전송
            eventCashRepository.save(id, notificationResponse); // 미전송 알림 저장용
        });
    }
}
