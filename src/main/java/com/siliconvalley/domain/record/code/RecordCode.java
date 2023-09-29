package com.siliconvalley.domain.record.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum RecordCode implements ResponseCode {

    CREATE_SUCCESS(201, "스테이지 기록 생성 및 리워드 지급에 성공했습니다.", HttpStatus.CREATED),
    UPDATE_SUCCESS(200, "스테이지 기록 변경 및 리워드 지급에 성공했습니다.", HttpStatus.OK);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    RecordCode(int code, String message, HttpStatus httpStatus) {
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
