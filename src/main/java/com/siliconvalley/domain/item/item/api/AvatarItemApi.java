package com.siliconvalley.domain.item.item.api;

import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.domain.image.service.S3PathBuildService;
import com.siliconvalley.domain.item.item.application.AvatarItemCreateService;
import com.siliconvalley.domain.item.item.dao.AvatarItemFindDao;
import com.siliconvalley.domain.item.item.dto.AvatarItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/items/avatars")
@RequiredArgsConstructor
public class AvatarItemApi {

    private final AvatarItemCreateService avatarItemCreateService;
    private final AvatarItemFindDao avatarItemFindDao;
    private final S3ImageUploadService s3ImageUploadService;
    private final S3PathBuildService s3PathBuildService;

    @PostMapping
    public ResponseEntity createAvatarItem(
            @RequestParam("avatarImage") MultipartFile avatarImage,
            @RequestParam("itemPrice") Long itemPrice,
            @RequestParam("avatarName") String avatarName
    ) throws IOException {
        String imgUrl = s3ImageUploadService.uploadFile(avatarImage, s3PathBuildService.buildPathForItem("avatar"));
        return ResponseEntity.status(HttpStatus.CREATED).body(avatarItemCreateService.createAvatarItem(itemPrice, avatarName, imgUrl));
    }

    @GetMapping
    public ResponseEntity getAllAvatarItems(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(avatarItemFindDao.getAvatarItemListByPage(pageable));
    }
}
