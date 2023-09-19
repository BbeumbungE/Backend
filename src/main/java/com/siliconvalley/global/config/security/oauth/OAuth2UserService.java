package com.siliconvalley.global.config.security.oauth;

import com.siliconvalley.domain.member.application.MemberSignUpService;
import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberSignUpService memberSignUpService;
    private final MemberFindDao memberFindDao;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String userEmail = getEmail(userRequest);
        Optional<Member> memberOptional = memberFindDao.getMemberOptionalByEmail(userEmail);
        Member member;

        // 회원가입 여부 확인
        if (memberOptional.isEmpty()) {
            member = memberSignUpService.signUp(userEmail);
        } else {
            System.out.println("로그인");
            member = memberOptional.get();
        }

        // Spring Security의 세션에 OAuth2User객체 저장
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRole())), OAuth2UserAttributeCreator.createAttribute(member), "id");
    }
    private String getEmail(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> kakao_account = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
        return (String) kakao_account.get("email");
    }
}
