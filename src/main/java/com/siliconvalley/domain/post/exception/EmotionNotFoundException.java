package com.siliconvalley.domain.post.exception;


import com.siliconvalley.global.error.exception.EntityNotFoundException;
import com.siliconvalley.global.error.exception.ErrorCode;

public class EmotionNotFoundException extends EntityNotFoundException {
    public EmotionNotFoundException(String message) {super(message + "is not found");}
}
