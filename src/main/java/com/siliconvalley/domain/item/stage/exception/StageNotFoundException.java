package com.siliconvalley.domain.item.stage.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class StageNotFoundException extends EntityNotFoundException {
    public StageNotFoundException(Long id) {
        super(id + " is not found.");
    }
}
