package com.siliconvalley.domain.profile.dto;

import com.siliconvalley.domain.member.dto.MemberResponse;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileResponse {

    private Long id;
    private String profileName;
    private MemberResponse member;
    private ProfileAvatarItemResponse profileAvatar;

    public ProfileResponse(final Profile profile) {
        this.id = profile.getId();
        this.profileName = profile.getProfileName();
        this.member = new MemberResponse(profile.getMember());
        this.profileAvatar = new ProfileAvatarItemResponse(profile.getProfileItem());
    }
}
