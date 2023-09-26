package com.siliconvalley.domain.profile.application;

import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.exception.ProfileNameDuplicateException;
import com.siliconvalley.domain.profile.exception.ProfileNameIncludeBadWordException;
import com.vane.badwordfiltering.BadWordFiltering;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileNameFilterService {

    private final ProfileFindDao profileFindDao;

    public void profileNameFilter(String profileName){
        if (duplicateFilter(profileName)) throw new ProfileNameDuplicateException(profileName);
        if (badWordFilter(profileName)) throw new ProfileNameIncludeBadWordException(profileName);
    }

    private boolean badWordFilter(String profileName){
        BadWordFiltering badWordFiltering = new BadWordFiltering();
        return badWordFiltering.check(profileName);
    }

    private boolean duplicateFilter(String profileName){
        return profileFindDao.existsByProfileName(profileName);
    }

}
