package com.siliconvalley.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.siliconvalley.global.config.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Date;

@SpringBootTest
@DisplayName("Validate Token Test")
public class ValidateTokenTest {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Value("${spring.jwt.secret}")
    private String secretKey;
    String wrongSecretKeyToken;
    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    public void setBefore() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    @DisplayName("accessToken이 만료되면 실패")
    public void 토큰_만료_예외() {
        // given
        String expiredToken = JWT.create()
                .withSubject("Access")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1))
                .withClaim("userId", "1")
                .withClaim("role", "ROLE_USER")
                .sign(Algorithm.HMAC512(secretKey));

        // when
        String accessToken = jwtTokenProvider.validateToken(expiredToken, request, response);
        System.out.println("accessToken = " + accessToken);

        // then
        Assertions.assertEquals(accessToken, null);
    }

    @Test
    @DisplayName("accessToken의 서명이 다르면 실패")
    public void 잘못된_토큰_예외() {
        // given
        String wrongSecretKeyToken = JWT.create()
                .withSubject("Access")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60))
                .withClaim("userId", "1")
                .withClaim("role", "ROLE_USER")
                .sign(Algorithm.HMAC512("fakeKey"));

        // when
        String accessToken = jwtTokenProvider.validateToken(wrongSecretKeyToken, request, response);
        System.out.println("accessToken = " + accessToken);

        // then
        Assertions.assertEquals(accessToken, null);
    }
}
