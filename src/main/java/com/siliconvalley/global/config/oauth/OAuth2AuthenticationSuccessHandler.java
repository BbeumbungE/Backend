package com.siliconvalley.global.config.oauth;

import com.siliconvalley.global.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        System.out.println("OAuth2AuthenticationSuccessHandler : 로그인 성공");

        // 이미 응답이 커밋됐는데 response하면 예외 발생하므로 return
        if (response.isCommitted()) {
            log.debug("Response has already been committed");
            return;
        }

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // token 생성
        String accessToken = jwtTokenProvider.createAccessToken(oAuth2User);
        jwtTokenProvider.createRefreshToken(oAuth2User, accessToken, request);

        // token 발급 or 예외 리다이렉트
        if (request.getAttribute("exception") != null) {
            sendRedirectUrl(request, response);
        } else {
            sendRedirectUrl(request, response, accessToken);
        }
    }

    private void sendRedirectUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUrl = "http://localhost:3000/oauth/redirect";
        String exception = (String) request.getAttribute("exception");
        redirectUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                .queryParam("exception", exception)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private void sendRedirectUrl(HttpServletRequest request, HttpServletResponse response, String accessToken) throws IOException {
        String redirectUrl = "http://localhost:3000/oauth/redirect";
        redirectUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                .queryParam("accessToken", accessToken)
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
