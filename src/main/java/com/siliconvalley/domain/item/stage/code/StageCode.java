package com.siliconvalley.domain.item.stage.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum StageCode implements ResponseCode {

    CREATE_SUCCESS(201, "스테이지 모드 주제로 변경했습니다.", HttpStatus.CREATED);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    StageCode(int code, String message, HttpStatus httpStatus) {
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
