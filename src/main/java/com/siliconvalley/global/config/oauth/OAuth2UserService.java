package com.siliconvalley.global.config.oauth;

import com.siliconvalley.domain.member.Member;
import com.siliconvalley.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String userEmail = getEmail(userRequest);
        Optional<Member> memberOptional = memberRepository.findByEmail(userEmail);
        Member member;

        // 회원가입 여부 확인
        if (memberOptional.isEmpty()) {
            member = signUp(userEmail);
        } else {
            System.out.println("그냥 로그인");
            member = memberOptional.get();
        }

        Map<String, Object> userAttributes = createAttribute(member);

        // Spring Security의 세션에 OAuth2User객체 저장
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRole())), userAttributes, "id");
    }

    private Map<String, Object> createAttribute(Member member) {
        Map<String, Object> attribute = new HashMap<>();
        attribute.put("id", member.getId());
        attribute.put("userId", member.getUserId());
        attribute.put("email", member.getEmail());
        attribute.put("role", member.getRole());
        return attribute;
    }

    private String getEmail(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> kakao_account = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
        return (String) kakao_account.get("email");
    }

    @Transactional
    public Member signUp(String email) {
        System.out.println("회원가입");
        String id = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        Member member = Member.builder()
                .id(id)
                .userId(userId)
                .email(email)
                .role("ROLE_USER")
                .build();

        return memberRepository.save(member);
    }


}
