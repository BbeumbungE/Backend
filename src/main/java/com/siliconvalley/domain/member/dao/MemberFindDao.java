package com.siliconvalley.domain.member.dao;

import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.member.dto.MemberResponse;
import com.siliconvalley.domain.member.exception.MemberNotFoundException;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFindDao {

    private final MemberRepository memberRepository;

    public Member findById(final String id) {
        final Optional<Member> memberOptional = memberRepository.findById(id);
        memberOptional.orElseThrow(() -> new MemberNotFoundException(id));
        return memberOptional.get();
    }

    public Member findByUserId(final String userId) {
        final Optional<Member> memberOptional = memberRepository.findByUserId(userId);
        memberOptional.orElseThrow(() -> new MemberNotFoundException(userId));
        return memberOptional.get();
    }

    public Optional<Member> getMemberOptionalByEmail(final String email) {
        final Optional<Member> memberOptional = memberRepository.findByEmail(email);
        return memberOptional;
    }

    public Response getMemberById(final String id) {
        final Optional<Member> memberOptional = memberRepository.findById(id);
        memberOptional.orElseThrow(() -> new MemberNotFoundException(id));
        return Response.of(CommonCode.GOOD_REQUEST, new MemberResponse(memberOptional.get()));
    }
}
