package com.siliconvalley.domain.item.item.dao;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.SubjectItemResponse;
import com.siliconvalley.domain.item.myitem.dao.MyItemFindDao;
import com.siliconvalley.domain.item.myitem.domain.MyItem;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.common.dto.page.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectItemFindDao {

    private final ItemRepository itemRepository;
    private final ItemFindDao itemFindDao;
    private final MyItemFindDao myItemFindDao;

    public Response getSubjectItemListByPage(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAllBySubjectIsNotNull(pageable);
        List<SubjectItemResponse> itemResponseList = itemPage.map(item -> new SubjectItemResponse(item)).getContent();
        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(itemResponseList, itemPage));
    }
    public Response getSubjectItemListByPage(Long profileId, Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAllBySubjectIsNotNull(pageable);
        List<SubjectItemResponse> itemResponseList = itemPage.map(item -> {
            boolean hasItem = myItemFindDao.checkHasItem(profileId, "subject", item);
            return new SubjectItemResponse(item, hasItem);
        }).getContent();
        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(itemResponseList, itemPage));
    }

    public Response getSubjectItemById(Long itemId) {
        Item item = itemFindDao.findById(itemId);
        return Response.of(CommonCode.GOOD_REQUEST, new SubjectItemResponse(item));
    }
}
