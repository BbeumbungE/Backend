package com.siliconvalley.domain.profile.application;

import com.siliconvalley.domain.member.domain.Member;
import com.siliconvalley.domain.profile.dao.ProfileRepository;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.dto.ProfileCreateOrUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileManagementService {

    private final ProfileRepository profileRepository;

    public Profile createProfile(final Member member, final ProfileCreateOrUpdate dto) {
        return profileRepository.save(dto.toEntity(member));
    }

    public Profile updateProfile(final Profile profile, final ProfileCreateOrUpdate dto) {
        profile.updateProfile(dto.getProfileName(), dto.getProfileImage());
        return profile;
    }

    public Profile deleteProfile(Profile profile) {
        profileRepository.delete(profile);
        SecurityContextHolder.clearContext();
        return profile;
    }
}
