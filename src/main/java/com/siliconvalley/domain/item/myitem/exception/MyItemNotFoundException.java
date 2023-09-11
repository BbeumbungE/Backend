package com.siliconvalley.domain.item.myitem.exception;

import com.siliconvalley.global.error.exception.EntityNotFoundException;

public class MyItemNotFoundException extends EntityNotFoundException {
    public MyItemNotFoundException(Long id) {
        super(id + " is not found");
    }
}
