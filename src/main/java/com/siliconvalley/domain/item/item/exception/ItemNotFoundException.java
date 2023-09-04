package com.siliconvalley.domain.item.item.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class ItemNotFoundException extends EntityNotFoundException {
    public ItemNotFoundException(Long target) {
        super(target + " is not found");
    }
}
