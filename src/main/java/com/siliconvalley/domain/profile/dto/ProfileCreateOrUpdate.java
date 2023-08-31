package com.siliconvalley.domain.profile.dto;

import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ProfileCreateOrUpdate {

    @Valid
    private String profileName;

    @Valid
    private String profileImage;

    ProfileCreateOrUpdate(@Valid String profileName, @Valid String profileImage) {
        this.profileName = profileName;
        this.profileImage = profileImage;
    }

    public Profile toEntity(final Member member) {
        return Profile.builder()
                .profileName(profileName)
                .profileImage(profileImage)
                .member(member)
                .build();
    }
}
