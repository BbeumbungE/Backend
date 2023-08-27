package com.siliconvalley.global.common.code;

import org.springframework.http.HttpStatus;

public interface ResponseCode {
    /**
     * 요청 처리 결과에 대한 코드값으로 HTTP Status 코드와는 별개로 보다 상세한 정보를 나타내야 한다.
     *
     * @return code 요청 처리 결과 코드
     */
    int getCode();

    /**
     * code 가 어떤 것을 의미하는지 나타내야 한다.
     *
     * @return message 요청 처리 결과 메시지
     */
    String getMessage();

    HttpStatus getHttpStatus();
}

