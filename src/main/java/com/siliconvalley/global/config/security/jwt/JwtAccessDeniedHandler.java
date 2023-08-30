package com.siliconvalley.global.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliconvalley.global.config.security.response.JsonResponser;
import com.siliconvalley.global.error.ErrorResponse;
import com.siliconvalley.global.error.exception.ErrorCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("권한없음");
        JsonResponser.sendJsonResponse(response, ErrorCode.HANDLE_ACCESS_DENIED);
    }
}
