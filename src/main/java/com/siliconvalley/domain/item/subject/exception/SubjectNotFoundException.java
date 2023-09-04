package com.siliconvalley.domain.item.subject.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class SubjectNotFoundException extends EntityNotFoundException {
    public SubjectNotFoundException(Long target) {
        super(target + " is not found");
    }
}
