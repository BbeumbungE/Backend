package com.siliconvalley.global.config.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliconvalley.global.error.ErrorResponse;
import com.siliconvalley.global.error.exception.ErrorCode;
import org.springframework.data.redis.RedisConnectionFailureException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("인증안됨");

        Exception e = (Exception) request.getAttribute("exception");
        if (e instanceof JWTDecodeException) {
            sendJsonResponse(response, ErrorCode.INVALID_TOKEN);
        } else if (e instanceof RedisConnectionFailureException) {
            sendJsonResponse(response, ErrorCode.UNCONNECTED_REDIS);
        } else if (e instanceof TokenExpiredException) {
            sendJsonResponse(response, ErrorCode.EXPIRED_TOKEN);
        } else {
            sendJsonResponse(response, ErrorCode.UNAUTHORIZATION);
        }
    }

    private void sendJsonResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json"); // JSON 형식의 데이터라고 설정
        response.setCharacterEncoding("UTF-8"); // 인코딩 설정
        response.setStatus(errorCode.getStatus());
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorCode));
    }
}
