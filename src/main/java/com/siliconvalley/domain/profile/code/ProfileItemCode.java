package com.siliconvalley.domain.profile.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum ProfileItemCode implements ResponseCode {

    PATCH_SUCCESS(204, "프로필 아바타 수정에 성공했습니다.", HttpStatus.NO_CONTENT);

    private final int code;
    private final String message;

    private final HttpStatus httpStatus;

    ProfileItemCode(int code, String message, HttpStatus httpStatus) {
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
