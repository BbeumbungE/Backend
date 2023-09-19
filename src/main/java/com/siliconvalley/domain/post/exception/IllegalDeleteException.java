package com.siliconvalley.domain.post.exception;

import com.siliconvalley.global.error.exception.ErrorCode;
import com.siliconvalley.global.error.exception.InvalidValueException;

public class IllegalDeleteException extends InvalidValueException {

    public IllegalDeleteException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public IllegalDeleteException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
