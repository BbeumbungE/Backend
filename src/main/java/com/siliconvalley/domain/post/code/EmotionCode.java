package com.siliconvalley.domain.post.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum EmotionCode implements ResponseCode {

    EMOTE_SUCCESS(201, "감정표현 처리 성공", HttpStatus.CREATED),
    GET_EMOTION_TYPE(200, "감정표현 타입 조회 성공", HttpStatus.OK),
    CANCEL_EMOTION_SUCCESS(204, "감정표현 취소 성공", HttpStatus.NO_CONTENT),
    UPDATE_EMOTION_SUCCESS(204, "감정표현 갱신 성공", HttpStatus.NO_CONTENT);

    private final int code;
    private final String message;

    private final HttpStatus httpStatus;

    EmotionCode(int code, String message, HttpStatus httpStatus) {
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
