package com.siliconvalley.domain.item.item.dao;

import com.siliconvalley.domain.item.item.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAllByAvatarIsNotNull(Pageable pageable);
    Page<Item> findAllBySubjectIsNotNull(Pageable pageable);

    List<Item> findAllByAvatarIsNotNullAndItemPrice(Long itemPrice);
    List<Item> findAllBySubjectIsNotNullAndItemPrice(Long itemPrice);
}
