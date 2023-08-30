package com.siliconvalley.domain.member.dao;

import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.member.exception.MemberNotFoundException;
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
        final Optional<Member> member = memberRepository.findById(id);
        member.orElseThrow(() -> new MemberNotFoundException(id));
        return member.get();
    }

    public Member findByUserId(final String userId) {
        final Optional<Member> member = memberRepository.findByUserId(userId);
        member.orElseThrow(() -> new MemberNotFoundException(userId));
        return member.get();
    }

    public Optional<Member> findByEmail(final String email) {
        final Optional<Member> memberOptional = memberRepository.findByEmail(email);
        return memberOptional;
    }
}
