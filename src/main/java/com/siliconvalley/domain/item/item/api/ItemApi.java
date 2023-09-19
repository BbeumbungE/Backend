package com.siliconvalley.domain.item.item.api;

import com.siliconvalley.domain.item.item.dao.AvatarItemFindDao;
import com.siliconvalley.domain.item.item.dao.SubjectItemFindDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemApi {

    private final AvatarItemFindDao avatarItemFindDao;
    private final SubjectItemFindDao subjectItemFindDao;

    @GetMapping("/{itemId}/avatars")
    public ResponseEntity getAvatarItem(
            @PathVariable("itemId") Long itemId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(avatarItemFindDao.getAvatarItemById(itemId));
    }

    @GetMapping("/{itemId}/subjects")
    public ResponseEntity getSubjectItem(
            @PathVariable("itemId") Long itemId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectItemFindDao.getSubjectItemById(itemId));
    }


}
