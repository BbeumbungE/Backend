package com.siliconvalley.domain.item.subject.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum SketchCode implements ResponseCode {
    CREATE_SUCCESS(201, "스케치 생성에 성공했습니다.", HttpStatus.CREATED);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    SketchCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }
}
