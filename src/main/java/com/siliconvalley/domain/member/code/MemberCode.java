package com.siliconvalley.domain.member.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum MemberCode implements ResponseCode {

    DELETE_SUCCESS(204, "회원 삭제에 성공했습니다.", HttpStatus.NO_CONTENT);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    MemberCode(int code, String message, HttpStatus httpStatus) {
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
