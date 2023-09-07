package com.siliconvalley.domain.member.api;

import com.siliconvalley.domain.member.application.MemberDeleteService;
import com.siliconvalley.domain.member.dao.MemberFindDao;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberDeleteService memberDeleteService;
    private final MemberFindDao memberFindDao;
    private final ProfileFindDao profileFindDao;

    @GetMapping
    public ResponseEntity getMember(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String id = (String) oAuth2User.getAttributes().get("id");
        return ResponseEntity.status(HttpStatus.OK).body(memberFindDao.getMemberById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteMember(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String id = (String) oAuth2User.getAttributes().get("id");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(memberDeleteService.deleteMember(id));
    }

    @GetMapping("/profiles")
    public ResponseEntity getProfilesOfMember(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String id = (String) oAuth2User.getAttributes().get("id");
        return ResponseEntity.status(HttpStatus.OK).body(profileFindDao.getProfilesByMemberId(id));
    }
}
