package com.siliconvalley.domain.member.application;

import com.siliconvalley.domain.member.code.MemberCode;
import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.member.dao.MemberRepository;
import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.member.dto.MemberResponse;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.error.exception.EntityNotFoundException;
import com.siliconvalley.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberDeleteService {

    private final MemberRepository memberRepository;
    private final MemberFindDao memberFindDao;

    @Transactional
    public Response deleteMember(String memberId) {
        Member member = memberFindDao.findById(memberId);
        memberRepository.delete(member);
        SecurityContextHolder.clearContext();
        return Response.of(MemberCode.DELETE_SUCCESS, null);
    }
}
