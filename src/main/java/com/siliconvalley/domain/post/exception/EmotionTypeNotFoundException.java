package com.siliconvalley.domain.post.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class EmotionTypeNotFoundException extends EntityNotFoundException {
    public EmotionTypeNotFoundException(String message) {
        super(message + "is not found");
    }
}
