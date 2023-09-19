package com.siliconvalley.domain.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3PathBuildService {

    public String buildPath(Long profileId, String urlType){
        return "profile/" + profileId + "/" + urlType;
    }
    public String buildPathForItem(String itemType) {
        return "item/" + itemType;
    }
}
