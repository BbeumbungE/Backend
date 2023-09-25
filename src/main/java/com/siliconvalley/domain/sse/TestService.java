package com.siliconvalley.domain.sse;

import com.siliconvalley.domain.item.item.dao.ItemFindDao;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.domain.notification.domain.NotificationType;
import com.siliconvalley.domain.notification.dto.NotificationResponse;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.sse.application.SseEmitterFinder;
import com.siliconvalley.domain.sse.application.SseEmitterSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class TestService {

    private final SseEmitterFinder sseEmitterFinder;
    private final SseEmitterSender sseEmitterSender;
    private final ProfileFindDao profileFindDao;
    private final ItemFindDao itemFindDao;

    public void sernTestMessage(Long profileId) {
        System.out.println(sseEmitterFinder.findALl().size());
        SseEmitter sseEmitter = sseEmitterFinder.findByProfileId(profileId);
        String id = profileId + "_" + System.currentTimeMillis();
        Item avatarItem = itemFindDao.findById(4L);
        Item subjectItem = itemFindDao.findById(1L);
        Profile profile = profileFindDao.findById(profileId);
        Notification avatarNotification = Notification.toItemNotification(profile, avatarItem, NotificationType.NEW_AVATAR);
        Notification subjectNotification = Notification.toItemNotification(profile, subjectItem, NotificationType.NEW_SUBJECT);
        Notification rankNotification = Notification.toRankingNotification(profile, NotificationType.RANKING);
        sseEmitterSender.send(sseEmitter, id, new NotificationResponse(avatarNotification),profileId, "massage");
        sseEmitterSender.send(sseEmitter, id, new NotificationResponse(subjectNotification), profileId, "massage");
        sseEmitterSender.send(sseEmitter, id, new NotificationResponse(rankNotification),profileId, "massage");
    }
}
