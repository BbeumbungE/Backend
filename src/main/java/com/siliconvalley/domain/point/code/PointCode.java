package com.siliconvalley.domain.point.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum PointCode implements ResponseCode {

    UPDATE_SUCCESS(204, "포인트 수정에 성공했습니다.", HttpStatus.NO_CONTENT);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    PointCode(int code, String message, HttpStatus httpStatus) {
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
