package com.siliconvalley.domain.item.item.dao;

import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.exception.ItemNotFoundException;
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
}
