package com.siliconvalley.domain.profile.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum ProfileCode implements ResponseCode {

    CREATE_SUCCESS(201, "프로필 생성에 성공하였습니다.",HttpStatus.CREATED),
    PATCH_SUCCESS(204, "프로필 수정에 성공했습니다.", HttpStatus.NO_CONTENT),
    DELETE_SUCCESS(204, "프로필 삭제에 성공하였습니다.", HttpStatus.NO_CONTENT);

    private final int code;
    private final String message;

    private final HttpStatus httpStatus;

    ProfileCode(int code, String message, HttpStatus httpStatus) {
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
