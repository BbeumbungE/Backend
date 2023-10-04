package com.siliconvalley.domain.profile.dto;

import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.domain.ProfileItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileCreate {

    @Valid
    private String profileName;

    public ProfileCreate(@Valid String profileName) {
        this.profileName = profileName;
    }

    public Profile toEntity(final Member member) {
        return Profile.builder()
                .profileName(profileName)
                .member(member)
                .build();
    }
}
