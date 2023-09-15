package com.siliconvalley.domain.sse.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum SseCode implements ResponseCode {
    CONNECT_SUCCESS(200, "연결에 성공했습니다.", HttpStatus.OK);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    SseCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}