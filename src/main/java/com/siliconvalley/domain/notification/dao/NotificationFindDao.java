package com.siliconvalley.domain.notification.dao;

import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.domain.notification.dto.NotificationResponse;
import com.siliconvalley.domain.notification.exception.NotificationNotFoundException;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.common.dto.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationFindDao {

    private final NotificationRepository notificationRepository;

    public Notification findById(Long notificationId) {
        Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);
        notificationOptional.orElseThrow(() -> new NotificationNotFoundException(notificationId));
        return notificationOptional.get();
    }

    public Response getNotificationPage(Long profileId, Pageable pageable) {
        Page<Notification> notificationPage = notificationRepository.findAllByReceiverId(profileId, pageable);
        List<NotificationResponse> notificationResponseList = notificationPage
                .map(notification -> new NotificationResponse(notification))
                .toList();
        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(notificationResponseList, notificationPage));
    }

    public Response getNotification(Long notificationId) {
        Notification notification = findById(notificationId);
        if (!notification.isRead()) notification.read(); // 읽음 처리
        return Response.of(CommonCode.GOOD_REQUEST, new NotificationResponse(notification));
    }

}
