package com.siliconvalley.domain.post.code;

import com.siliconvalley.global.common.code.ResponseCode;
import org.springframework.http.HttpStatus;

public enum PostCode implements ResponseCode {

    POSTING_SUCCESS(201, "게시물 포스팅에 성공하였습니다.", HttpStatus.CREATED),
    DELETE_POST_SUCCESS(204, "게시물 삭제에 성공하였습니다.", HttpStatus.NO_CONTENT),
    POST_RETRIEVE_SUCCESS(200, "게시글 리스트 조회에 성공하였습니다.", HttpStatus.OK),
    GET_POST_DETAIL_SUCCESS(200, "게시글 상세 조회에 성공하였습니다.", HttpStatus.OK)
    ;

    private final int code;
    private final String message;

    private final HttpStatus httpStatus;

    PostCode(int code, String message, HttpStatus httpStatus) {
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
