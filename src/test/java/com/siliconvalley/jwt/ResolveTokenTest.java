package com.siliconvalley.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.siliconvalley.global.config.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Date;

@SpringBootTest
@DisplayName("Resolve Token Test")
public class ResolveTokenTest {

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    String token;
    MockHttpServletRequest request;

    @BeforeEach
    public void setBefore() {
        token = JWT.create()
                .withSubject("Access")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60))
                .withClaim("userId", "1")
                .withClaim("role", "ROLE_USER")
                .sign(Algorithm.HMAC512("secretKey"));

        request = new MockHttpServletRequest();
    }

    @Test
    @DisplayName("Header가 Authorization이 아닌 경우 실패")
    public void Header_Resolving_실패() {
        // Given
        request.addHeader("NotAuthorization", token);

        // When
        String accessToken = jwtTokenProvider.resolveToken(request);

        // Then
        assertEquals(accessToken, null);
    }

    @Test
    @DisplayName("Prefix가 Bearer가 아닌 경우 실패")
    public void Prefix_Resolving_실패() {
        // Given
        token = "NotBearer " + token;
        request.addHeader("Authorization", token);

        // When
        String accessToken = jwtTokenProvider.resolveToken(request);

        // Then
        assertEquals(accessToken, null);
    }

    @Test
    @DisplayName("Header가 Authorization이고 Prefix가 Bearer인 경우 성공")
    public void Resolving_성공() {
        // Given
        token = "Bearer " + token;
        request.addHeader("Authorization", token);

        // When
        String accessToken = jwtTokenProvider.resolveToken(request);

        // Then
        assertNotSame(accessToken, null);
    }
}
