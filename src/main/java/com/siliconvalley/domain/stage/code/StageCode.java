package com.siliconvalley.domain.stage.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum StageCode implements ResponseCode {

    CREATE_SUCCESS(201, "스테이지 생성에 성공했습니다.", HttpStatus.CREATED),
    UPDATE_SUCCESS(204, "스테이지의 그림주제 변경에 성공했습니다.", HttpStatus.NO_CONTENT);

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
