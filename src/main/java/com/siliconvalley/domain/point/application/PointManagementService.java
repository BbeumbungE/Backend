package com.siliconvalley.domain.point.application;

import com.siliconvalley.domain.point.code.PointCode;
import com.siliconvalley.domain.point.domain.Point;
import com.siliconvalley.domain.point.dto.PointResponse;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PointManagementService {

    private final ProfileFindDao profileFindDao;

    public Response getPoint(Long profileId) {
        Profile profile = profileFindDao.findById(profileId);
        return Response.of(CommonCode.GOOD_REQUEST, new PointResponse(profile.getPoint()));
    }

    public Response updatePoint(Long profileId, Long newPointValue) {
        Profile profile = profileFindDao.findById(profileId);
        Point point = profile.getPoint();
        point.updatePoint(newPointValue);
        return Response.of(PointCode.UPDATE_SUCCESS);
    }
}
