package com.siliconvalley.domain.item.item.dao;

import com.siliconvalley.domain.item.item.dto.AvatarItemResponse;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.exception.InvalidCategoryException;
import com.siliconvalley.domain.item.item.exception.ItemNotFoundException;
import com.siliconvalley.domain.item.item.dto.RankSubjectItemResponse;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemFindDao {

    private final ItemRepository itemRepository;

    public Item findById(Long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        itemOptional.orElseThrow(() -> new ItemNotFoundException(itemId));
        return itemOptional.get();
    } 

    public Response getItemById(Long itemId, String category) {
        Item item = findById(itemId);
        Object response;
        if (category.equals("avatar")) {
            response = new AvatarItemResponse(item);
        } else if (category.equals("subject")) {
            response = new RankSubjectItemResponse(item);
        } else {
            throw new InvalidCategoryException(category);
        }

        return Response.of(CommonCode.GOOD_REQUEST, response);
    }
}
