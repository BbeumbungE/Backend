package com.siliconvalley.domain.record.exception;

import com.siliconvalley.global.error.exception.InvalidValueException;

public class RecordAlreadyExist extends InvalidValueException {
    public RecordAlreadyExist(String value) {
        super(value + "이 이미 존재합니다");
    }
}
