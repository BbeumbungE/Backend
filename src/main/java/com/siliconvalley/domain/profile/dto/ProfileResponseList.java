package com.siliconvalley.domain.profile.dto;

import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileResponseList {

    private List<ProfileResponse> profileList;

    public ProfileResponseList(final List<Profile> profileList) {
        this.profileList = profileList.stream()
                .map(profile -> new ProfileResponse(profile))
                .collect(Collectors.toList());
    }
}
