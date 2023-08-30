package com.siliconvalley.domain.member.api;

import com.siliconvalley.domain.member.application.MemberDeleteService;
import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.member.dto.MemberResponse;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.code.ResponseCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberDeleteService memberDeleteService;
    private final MemberFindDao memberFindDao;

    @GetMapping
    public Response getMember(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String id = (String) oAuth2User.getAttributes().get("id");
        MemberResponse memberResponse = new MemberResponse(memberFindDao.findById(id));
        return Response.of(CommonCode.GOOD_REQUEST, memberResponse);
    }

    @DeleteMapping
    public Response delete(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String id = (String) oAuth2User.getAttributes().get("id");
        MemberResponse memberResponse = new MemberResponse(memberDeleteService.deleteMember(id));
        return Response.of(CommonCode.GOOD_REQUEST, memberResponse);
    }
}
