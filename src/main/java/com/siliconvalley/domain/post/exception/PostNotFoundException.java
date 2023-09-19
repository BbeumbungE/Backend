package com.siliconvalley.domain.post.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class PostNotFoundException extends EntityNotFoundException {
    public PostNotFoundException(String message) {
        super(message + "is not found");
    }
}