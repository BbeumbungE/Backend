package com.siliconvalley.domain.sse.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum SseCode implements ResponseCode {
    CONNECT_SUCCESS(200, "연결에 성공했습니다.", HttpStatus.OK),
    TEMP_ID_GENERATE_SUCCESS(201, "임시 아이디 발급에 성공하였습니다.", HttpStatus.CREATED),
    CONNECT_FAIL(400, "연결에 실패했습니다.", HttpStatus.BAD_REQUEST);

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
