package com.siliconvalley.domain.post.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum RankingCode implements ResponseCode {
    UPDATE_RANKING(201, "랭킹이 업데이트 되었습니다.", HttpStatus.CREATED),
    GET_RANKING_SUCCESS(200, "랭킹 조회에 성공하였습니다.", HttpStatus.OK);

    private final int code;
    private final String message;

    private final HttpStatus httpStatus;

    RankingCode(int code, String message, HttpStatus httpStatus) {
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
