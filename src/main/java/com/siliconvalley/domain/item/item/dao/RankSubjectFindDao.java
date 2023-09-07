package com.siliconvalley.domain.item.item.dao;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.RankSubjectItemResponse;
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
public class RankSubjectFindDao {

    private final ItemRepository itemRepository;

    public Response getSubjectItemListByPage(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAllBySubjectIsNotNull(pageable);
        List<RankSubjectItemResponse> itemResponseList = itemPage.map(item -> new RankSubjectItemResponse(item)).getContent();
        return Response.of(CommonCode.GOOD_REQUEST, new PageResponse(itemResponseList, itemPage));
    }
}
