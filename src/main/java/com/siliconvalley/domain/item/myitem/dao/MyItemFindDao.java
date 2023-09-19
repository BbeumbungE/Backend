package com.siliconvalley.domain.item.myitem.dao;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.domain.item.myitem.dto.MyAvatarItemResponse;
import com.siliconvalley.domain.item.myitem.dto.MySubjectItemResponse;
import com.siliconvalley.domain.item.myitem.exception.MyItemNotFoundException;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.common.dto.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyItemFindDao {

    private final MyItemRepository myItemRepository;

    public MyItem findById(Long myItemId) {
        Optional<MyItem> myItemOptional = myItemRepository.findById(myItemId);
        myItemOptional.orElseThrow(() -> new MyItemNotFoundException(myItemId));
        return myItemOptional.get();
    }

    public Response getMyItemListByPage(Long profileId, Pageable pageable, String category) {
        Page<MyItem> myItemPage = myItemRepository.findByProfileIdAndItemType(profileId, category, pageable);
        List myItemResponseList = new ArrayList();

        if (category.equals("avatar")) {
            myItemResponseList = myItemPage.map(myItem -> new MyAvatarItemResponse(myItem)).getContent();
        } else if (category.equals("subject")) {
            myItemResponseList = myItemPage.map(myItem -> new MySubjectItemResponse(myItem)).getContent();
        }
        PageResponse pageResponse = new PageResponse(myItemResponseList,myItemPage);
        return Response.of(CommonCode.GOOD_REQUEST, pageResponse);
    }
    public boolean checkHasItem(Long profileId, String category, Item item) {
        List<MyItem> myItemList = myItemRepository.findByProfileIdAndItemType(profileId, category);
        boolean hasItem = false;
        for (MyItem myItem : myItemList) {
            if (Objects.equals(myItem.getItem(), item)) {
                hasItem = true;
                break;
            }
        }
        return hasItem;
    }
}
