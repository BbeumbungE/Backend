package com.siliconvalley.domain.point.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class PointNotFoundException extends EntityNotFoundException {
    public PointNotFoundException(String message) {
        super(message + " is not found");
    }
}
