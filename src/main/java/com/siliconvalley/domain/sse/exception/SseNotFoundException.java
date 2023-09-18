package com.siliconvalley.domain.sse.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class SseNotFoundException extends EntityNotFoundException {
    public SseNotFoundException(String message){super(message + "is not found");}
}
