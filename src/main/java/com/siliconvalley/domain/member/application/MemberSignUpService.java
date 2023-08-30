package com.siliconvalley.domain.member.application;

import com.siliconvalley.domain.member.dao.MemberRepository;
import com.siliconvalley.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    private final MemberRepository memberRepository;

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
