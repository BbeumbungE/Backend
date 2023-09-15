package com.siliconvalley.domain.notification.application;

import com.siliconvalley.domain.notification.code.NotificationCode;
import com.siliconvalley.domain.notification.dao.NotificationFindDao;
import com.siliconvalley.domain.notification.dao.NotificationRepository;
import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationDeleteService {

    private final NotificationRepository notificationRepository;
    private final NotificationFindDao notificationFindDao;

    public Response deleteNotification(Long notificationId) {
        Notification notification = notificationFindDao.findById(notificationId);
        notificationRepository.delete(notification);
        return Response.of(NotificationCode.DELETE_SUCCESS, null);
    }
}
