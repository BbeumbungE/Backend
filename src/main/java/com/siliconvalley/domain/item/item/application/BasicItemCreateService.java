package com.siliconvalley.domain.item.item.application;

import com.siliconvalley.domain.item.item.dao.ItemFindDao;
import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.profile.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BasicItemCreateService {

    private final ItemFindDao itemFindDao;

    public MyItem createBasicAvatarItem(Profile profile) {
        return MyItem.builder()
                .profile(profile)
                .itemType("avatar")
                .item(itemFindDao.findById(1L))
                .build();
    }

    public MyItem createBasicSubjectItem(Profile profile) {
        return MyItem.builder()
                .profile(profile)
                .itemType("subject")
                .item(itemFindDao.findById(2L))
                .build();
    }
}
