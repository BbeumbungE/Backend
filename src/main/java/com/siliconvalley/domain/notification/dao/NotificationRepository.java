package com.siliconvalley.domain.notification.dao;

import com.siliconvalley.domain.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
