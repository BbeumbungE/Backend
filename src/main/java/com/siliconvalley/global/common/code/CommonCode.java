package com.siliconvalley.global.common.code;

import org.springframework.http.HttpStatus;

public enum CommonCode implements ResponseCode{
    GOOD_REQUEST(200, "올바른 요청입니다.", HttpStatus.OK),
    SUCCESS_CREATE(201, "생성 성공했습니다.", HttpStatus.CREATED),
    SUCCESS_UPDATE(204, "수정 완료했습니다.", HttpStatus.NO_CONTENT),
    SUCCESS_DELETE(204, "삭제 완료했습니다.", HttpStatus.NO_CONTENT),
    VALIDATION_FAIL(400, "입력값 검증이 실패하였습니다.", HttpStatus.BAD_REQUEST),
    BAD_REQUEST(400, "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    ILLEGAL_REQUEST(422, "잘못된 데이터가 포함된 요청입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
    private final int code;
    private final String message;

    private final HttpStatus httpStatus;

    CommonCode(int code, String message, HttpStatus httpStatus) {
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
    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    };
}
