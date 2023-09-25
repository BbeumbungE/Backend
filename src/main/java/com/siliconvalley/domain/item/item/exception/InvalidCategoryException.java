package com.siliconvalley.domain.item.item.exception;

import com.siliconvalley.global.error.exception.ErrorCode;
import com.siliconvalley.global.error.exception.InvalidValueException;

public class InvalidCategoryException extends InvalidValueException {
    public InvalidCategoryException(String value) {
        super(value, ErrorCode.INVALID_CATEGORY);
    }
}
