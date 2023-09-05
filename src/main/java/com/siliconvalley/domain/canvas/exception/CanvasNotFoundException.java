package com.siliconvalley.domain.canvas.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class CanvasNotFoundException extends EntityNotFoundException {
    public CanvasNotFoundException(String target){
        super(target + "is not found");
    }
}
