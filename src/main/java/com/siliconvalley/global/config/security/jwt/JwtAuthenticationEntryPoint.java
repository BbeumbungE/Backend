package com.siliconvalley.global.config.security.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siliconvalley.global.config.security.response.JsonResponser;
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
            JsonResponser.sendJsonResponse(response, ErrorCode.INVALID_TOKEN);
        } else if (e instanceof RedisConnectionFailureException) {
            JsonResponser.sendJsonResponse(response, ErrorCode.UNCONNECTED_REDIS);
        } else if (e instanceof TokenExpiredException) {
            JsonResponser.sendJsonResponse(response, ErrorCode.EXPIRED_TOKEN);
        } else if (e instanceof SignatureVerificationException){
            JsonResponser.sendJsonResponse(response, ErrorCode.INVALID_TOKEN);
        } else {
            JsonResponser.sendJsonResponse(response, ErrorCode.UNAUTHORIZATION);
        }
    }
}
