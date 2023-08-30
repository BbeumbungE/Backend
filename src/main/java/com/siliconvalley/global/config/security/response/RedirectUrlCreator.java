package com.siliconvalley.global.config.security.response;

import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RedirectUrlCreator {
    public static String createTargetUrl(HttpServletRequest request) {
        String redirectUrl = "http://localhost:3000/oauth/redirect";
        String exception = (String) request.getAttribute("exception");
        redirectUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                .queryParam("exception", exception)
                .build().toUriString();

        return redirectUrl;
    }

    public static String createTargetUrl(String accessToken, String userId) throws IOException {
        String redirectUrl = "http://localhost:3000/oauth/redirect";
        redirectUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                .queryParam("accessToken", accessToken)
                .queryParam("userId", userId)
                .build().toUriString();

        return redirectUrl;
    }
}
