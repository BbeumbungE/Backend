package com.siliconvalley.domain.item.item.dao;

import com.siliconvalley.domain.item.item.dto.AvatarItemResponse;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.exception.InvalidCategoryException;
import com.siliconvalley.domain.item.item.exception.ItemNotFoundException;
import com.siliconvalley.domain.item.item.dto.RankSubjectItemResponse;
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

    public Object getItemById(Long itemId, String category) {
        Item item = findById(itemId);

        if (category.equals("avatar")) {
            return new AvatarItemResponse(item);
        } else if (category.equals("subject")) {
            return new RankSubjectItemResponse(item);
        } else {
            throw new InvalidCategoryException(category);
        }
    }
}
