package com.siliconvalley.domain.canvas.exception;

import com.siliconvalley.global.error.exception.ErrorCode;
import com.siliconvalley.global.error.exception.InvalidValueException;

public class CanvasIllegalDeleteException extends InvalidValueException {
    public CanvasIllegalDeleteException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

    public CanvasIllegalDeleteException(String value, ErrorCode errorCode) {
        super(value, errorCode);
    }
}
