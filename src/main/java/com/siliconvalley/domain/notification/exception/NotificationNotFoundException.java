package com.siliconvalley.domain.notification.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class NotificationNotFoundException extends EntityNotFoundException {
    public NotificationNotFoundException(Long id) {
        super(id + " is not found");
    }
}
