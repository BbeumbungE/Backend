package com.siliconvalley.domain.item.avatar.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class AvatarNotFoundException extends EntityNotFoundException {
    public AvatarNotFoundException(Long target) {
        super(target + " is not found");
    }
}
