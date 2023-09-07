package com.siliconvalley.domain.item.item.dao;

import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.AvatarItemResponse;
import com.siliconvalley.domain.item.item.exception.ItemNotFoundException;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.common.dto.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AvatarItemFindDao {

    private final ItemRepository itemRepository;

    public Response getAvatarItemListByPage(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAllByAvatarIsNotNull(pageable);
        List<AvatarItemResponse> itemResponseList = itemPage.map(item -> new AvatarItemResponse(item)).getContent();
        return Response.of(CommonCode.GOOD_REQUEST,new PageResponse(itemResponseList,itemPage));
    }
}
