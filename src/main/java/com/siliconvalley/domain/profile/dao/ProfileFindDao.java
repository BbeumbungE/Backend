package com.siliconvalley.domain.profile.dao;

import com.siliconvalley.domain.member.dao.MemberRepository;
import com.siliconvalley.domain.profile.code.ProfileCode;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.profile.dto.ProfileResponse;
import com.siliconvalley.domain.profile.dto.ProfileResponseList;
import com.siliconvalley.domain.profile.exception.ProfileNotFoundException;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileFindDao {

    private final ProfileRepository profileRepository;

    public Profile findById(final Long id) {
        final Optional<Profile> profileOptional = profileRepository.findById(id);
        profileOptional.orElseThrow(() -> new ProfileNotFoundException(id));
        return profileOptional.get();
    }

    public Optional<Profile> findOptionalById(final Long id) {
        return profileRepository.findById(id);
    }

    public Response getProfileById(final Long id) {
        final Optional<Profile> profileOptional = profileRepository.findById(id);
        profileOptional.orElseThrow(()-> new ProfileNotFoundException(id));
        return Response.of(CommonCode.GOOD_REQUEST, new ProfileResponse(profileOptional.get()));
    }

    public Response getProfilesByMemberId(String userId) {
        List<Profile> profileList = profileRepository.findByMemberId(userId);
        return Response.of(CommonCode.GOOD_REQUEST, new ProfileResponseList(profileList));
    }

    public boolean existsByProfileName(String profileName) {
        return profileRepository.existsByProfileName(profileName);
    }
}
