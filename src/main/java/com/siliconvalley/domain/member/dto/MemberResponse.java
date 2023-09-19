package com.siliconvalley.domain.member.dto;

import com.siliconvalley.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private String userId;
    private String email;

    public MemberResponse(final Member member) {
        this.userId = member.getUserId();
        this.email = member.getEmail();
    }
}
