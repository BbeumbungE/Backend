package com.siliconvalley.domain.item.myitem.dao;

import com.siliconvalley.domain.item.myitem.domain.MyItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyItemRepository extends JpaRepository<MyItem, Long> {
    Page<MyItem> findByProfileIdAndItemType(Long profileId, String itemType, Pageable pageable);
    List<MyItem> findByProfileIdAndItemType(Long profileId, String itemType);
}
