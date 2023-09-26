package com.siliconvalley.global.config.security.oauth;

import com.siliconvalley.global.config.security.jwt.JwtTokenProvider;
import com.siliconvalley.global.config.security.response.RedirectUrlCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedirectUrlCreator redirectUrlCreator;

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
        String redirectUrl;
        if (request.getAttribute("exception") != null) {
            redirectUrl = redirectUrlCreator.createTargetUrl(request);
        } else {
            redirectUrl = redirectUrlCreator.createTargetUrl(accessToken, (String) oAuth2User.getAttributes().get("userId"));
        }

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
