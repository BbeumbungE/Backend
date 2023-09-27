package com.siliconvalley.domain.rabbitMQ.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum RabbitMQCode implements ResponseCode {
    CONVERSION_REQUEST_SUCCESS(201, "변환 요청에 성공하였습니다.", HttpStatus.CREATED),
    CONVERSION_REQUEST_FAILURE(400, "변환 요청에 실패하였습니다.", HttpStatus.BAD_REQUEST),
    CONVERSION_RESPONSE_SUCCESS(200, "변환 응답을 성공적으로 수신하였습니다.", HttpStatus.OK),
    CONVERSION_RESPONSE_FAILURE(500, "변환 응답 수신에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    DEMO_CONVERSION_SUCCESS(200, "체험 변환 요청에 성공하였습니다.", HttpStatus.OK)
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    RabbitMQCode(int code, String message, HttpStatus httpStatus) {
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
