package com.siliconvalley.domain.profile.dto;

import com.siliconvalley.domain.item.myitem.dto.MyAvatarItemResponse;
import com.siliconvalley.domain.profile.domain.ProfileItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileAvatarItemResponse {

    private Long id;
    private MyAvatarItemResponse myAvatarItem;

    public ProfileAvatarItemResponse(ProfileItem profileItem) {
        this.id = profileItem.getId();
        this.myAvatarItem = new MyAvatarItemResponse(profileItem.getMyItem());
    }
}
