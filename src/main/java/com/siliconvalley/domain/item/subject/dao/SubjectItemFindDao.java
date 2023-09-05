package com.siliconvalley.domain.item.subject.dao;

import com.siliconvalley.domain.item.item.dao.ItemFindDao;
import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.subject.dto.SubjectItemResponse;
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

    private final ItemFindDao itemFindDao;
    private final ItemRepository itemRepository;

    public PageResponse getSubjectItemListByPage(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAllBySubjectIsNotNull(pageable);
        List<SubjectItemResponse> itemResponseList = itemPage.map(item -> new SubjectItemResponse(item)).getContent();
        return new PageResponse(itemResponseList, itemPage);
    }

    public SubjectItemResponse getSubjectItemById(Long itemId) {
        Item item = itemFindDao.findById(itemId);
        return new SubjectItemResponse(item);
    }
}
