package com.siliconvalley.domain.item.item.dao;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.exception.InvalidCategoryException;
import com.siliconvalley.domain.item.item.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public List<Item> findAllFreeItem(String itemType) {
        List<Item> itemList;
        if (itemType.equals("avatar")) {
            itemList = itemRepository.findAllByAvatarIsNotNullAndItemPrice(0L);
        } else if (itemType.equals("subject")) {
            itemList = itemRepository.findAllBySubjectIsNotNullAndItemPrice(0L);
        } else {
            throw new InvalidCategoryException(itemType);
        }
        return itemList;
    }
}
