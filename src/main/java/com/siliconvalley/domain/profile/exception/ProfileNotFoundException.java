package com.siliconvalley.domain.profile.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class ProfileNotFoundException extends EntityNotFoundException {
    public ProfileNotFoundException(Long target) {
        super(target + " is not found");
    }
}
