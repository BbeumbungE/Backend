package com.siliconvalley.domain.rabbitMQ.exception;

import com.siliconvalley.global.error.exception.BusinessException;
import com.siliconvalley.global.error.exception.ErrorCode;

public class MessageErrorException extends BusinessException {
    public MessageErrorException(String message){
        super(message, ErrorCode.RABBITMQ_ERROR); // 예를 들어 RABBITMQ_MESSAGE_ERROR라는 ErrorCode를 사용
    }
}
