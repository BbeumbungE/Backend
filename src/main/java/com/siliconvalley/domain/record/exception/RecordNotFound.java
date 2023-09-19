package com.siliconvalley.domain.record.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class RecordNotFound extends EntityNotFoundException {
    public RecordNotFound(Long id) {
        super(id + " is not found");
    }
}
