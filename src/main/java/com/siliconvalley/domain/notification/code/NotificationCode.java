package com.siliconvalley.domain.notification.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum NotificationCode implements ResponseCode {
    DELETE_SUCCESS(204, "알림이 삭제되었습니다", HttpStatus.NO_CONTENT);

    private int code;
    private String message;
    private HttpStatus httpStatus;

    NotificationCode(int code, String message, HttpStatus httpStatus) {
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
