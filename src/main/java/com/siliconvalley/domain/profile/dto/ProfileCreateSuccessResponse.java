package com.siliconvalley.domain.profile.dto;

import com.siliconvalley.domain.member.dto.MemberResponse;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileCreateSuccessResponse {
    private Long id;

    public ProfileCreateSuccessResponse(final Profile profile) {
        this.id = profile.getId();
    }
}
