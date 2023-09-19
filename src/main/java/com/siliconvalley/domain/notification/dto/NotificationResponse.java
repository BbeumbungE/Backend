package com.siliconvalley.domain.notification.dto;

import com.siliconvalley.domain.notification.domain.Notification;
import com.siliconvalley.domain.notification.domain.NotificationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationResponse {

    private Long id;
    private String content;
    private NotificationType type;
    private String receiver;
    private boolean isRead;
    private LocalDateTime createAt;

    public NotificationResponse(Notification notification) {
        this.id = notification.getId();
        this.content = notification.getContent();
        this.type = notification.getType();
        this.receiver = notification.getReceiver().getProfileName();
        this.isRead = notification.isRead();
        this.createAt = notification.getCreateAt();
    }
}
