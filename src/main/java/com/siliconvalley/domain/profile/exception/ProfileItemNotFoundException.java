package com.siliconvalley.domain.profile.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class ProfileItemNotFoundException extends EntityNotFoundException {
    public ProfileItemNotFoundException(Long id) {
        super(id + " is not found");
    }
}
