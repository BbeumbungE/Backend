package com.siliconvalley.domain.point.application;

import com.siliconvalley.domain.point.Point;
import com.siliconvalley.domain.point.PointResponse;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PointManagementService {

    private final ProfileFindDao profileFindDao;

    public PointResponse getPoint(Long profileId) {
        Profile profile = profileFindDao.findById(profileId);
        return new PointResponse(profile.getPoint());
    }

    public void updatePoint(Long profileId, Long newPointValue) {
        Profile profile = profileFindDao.findById(profileId);
        Point point = profile.getPoint();
        point.updatePoint(newPointValue);
    }
}
