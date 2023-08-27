package com.siliconvalley.domain.member.api;

import com.siliconvalley.domain.member.Member;
import com.siliconvalley.domain.member.MemberService;
import com.siliconvalley.domain.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberService memberService;

    @GetMapping("/{userId}")
    public MemberResponse getMember(
            @AuthenticationPrincipal OAuth2User oAuth2User,
            @PathVariable("userId") String userId
    ) {
        return new MemberResponse(memberService.getMember(userId));
    }

    @DeleteMapping
    public MemberResponse delete(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String userId = (String) oAuth2User.getAttributes().get("userId");
        return new MemberResponse(memberService.deleteMember(userId));
    }
}
