package com.siliconvalley.domain.member;

import com.siliconvalley.global.error.exception.EntityNotFoundException;
import com.siliconvalley.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    @Secured("ROLE_ADMIN")
    public Member getMember(String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
        return member;
    }
    @Transactional
    public Member deleteMember(String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
        memberRepository.delete(member);
        SecurityContextHolder.clearContext();
        return member;
    }
}
