package com.siliconvalley.domain.item.myitem.dao;

import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.item.myitem.dto.MyAvatarItemResponse;
import com.siliconvalley.domain.item.myitem.dto.MySubjectItemResponse;
import com.siliconvalley.global.common.dto.Page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyItemFindDao {

    private final MyItemRepository myItemRepository;

    public PageResponse getMyItemListByPage(Long profileId, Pageable pageable, String category) {
        Page<MyItem> myItemPage = myItemRepository.findByProfileIdAndItemType(profileId, category, pageable);
        List myItemResponseList = new ArrayList();

        if (category.equals("avatar")) {
            myItemResponseList = myItemPage.map(myItem -> new MyAvatarItemResponse(myItem)).getContent();
        } else if (category.equals("subject")) {
            myItemResponseList = myItemPage.map(myItem -> new MySubjectItemResponse(myItem)).getContent();
        }

        return new PageResponse(myItemResponseList, myItemPage);
    }
}
