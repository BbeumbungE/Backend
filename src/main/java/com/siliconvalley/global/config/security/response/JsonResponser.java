package com.siliconvalley.global.config.security.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliconvalley.global.error.ErrorResponse;
import com.siliconvalley.global.error.exception.ErrorCode;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonResponser {
    public static void sendJsonResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json"); // JSON 형식의 데이터라고 설정
        response.setCharacterEncoding("UTF-8"); // 인코딩 설정
        response.setStatus(errorCode.getStatus());
        response.getWriter().write(new ObjectMapper().writeValueAsString(ErrorResponse.of(errorCode)));
    }
}
