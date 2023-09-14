package com.siliconvalley.domain.item.myitem.application;

import com.siliconvalley.domain.item.item.dao.ItemFindDao;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.myitem.code.MyItemCode;
import com.siliconvalley.domain.item.myitem.dao.MyItemRepository;
import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.item.myitem.dto.MyItemPostSuccessResponse;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyItemCreateService {

    private final MyItemRepository myItemRepository;
    private final ProfileFindDao profileFindDao;
    private final ItemFindDao itemFindDao;

    public Response createMyItem(Long profileId, Long itemId, String itemType) {
        Profile profile = profileFindDao.findById(profileId);
        Item item = itemFindDao.findById(itemId);
        MyItem myItem = MyItem.builder()
                .itemType(itemType)
                .profile(profile)
                .item(item)
                .build();

        myItemRepository.save(myItem);
        return Response.of(MyItemCode.CREATE_SUCCESS, new MyItemPostSuccessResponse(myItem));
    }

    public List<MyItem> createBasicItem(Profile profile, String itemType) {
        return itemFindDao.findAllFreeItem(itemType).stream()
                .map(item -> MyItem.toEntity(profile, item, itemType))
                .collect(Collectors.toList());
    }
}
