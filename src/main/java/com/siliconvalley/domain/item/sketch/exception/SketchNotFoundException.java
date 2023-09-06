package com.siliconvalley.domain.item.sketch.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class SketchNotFoundException extends EntityNotFoundException {
    public SketchNotFoundException(Long target) {
        super(target + " is not found");
    }
}
