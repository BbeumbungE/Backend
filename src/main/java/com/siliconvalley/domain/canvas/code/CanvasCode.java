package com.siliconvalley.domain.canvas.code;


import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum CanvasCode implements ResponseCode {
    GET_CANVAS_SUCCESS(200, "그림 조회에 성공하였습니다.", HttpStatus.OK),
    SAVE_CANVAS_SUCCESS(201, "그림 저장에 성공하였습니다.", HttpStatus.CREATED),
    DELETE_CANVAS_SUCCESS(204, "그림 삭제에 성공하였습니다.", HttpStatus.NO_CONTENT),
    TRANSFORM_CANVAS_SUCCESS(204, "그림 변환에 성공하였습니다.", HttpStatus.NO_CONTENT),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    CanvasCode(int code, String message, HttpStatus httpStatus) {
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
