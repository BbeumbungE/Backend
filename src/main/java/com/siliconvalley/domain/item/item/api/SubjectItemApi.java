package com.siliconvalley.domain.item.item.api;

import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.domain.image.service.S3PathBuildService;
import com.siliconvalley.domain.item.item.dao.SubjectItemFindDao;
import com.siliconvalley.domain.item.item.application.SubjectItemCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/items/subjects")
@RequiredArgsConstructor
public class SubjectItemApi {

    private final SubjectItemCreateService subjectItemCreateService;
    private final SubjectItemFindDao subjectItemFindDao;
    private final S3ImageUploadService s3ImageUploadService;
    private final S3PathBuildService s3PathBuildService;

    /**
     * Rank Subject Management
     **/

    // 새 subject 아이템 생성 // admin 가능하게 권한 설정 필요
    @PostMapping
    public ResponseEntity createSubjectItem(
            @RequestParam("subjectImage") MultipartFile subjectImage,
            @RequestParam("itemPrice") Long itemPrice,
            @RequestParam("subjectName") String subjectName,
            @RequestParam("modelName") String modelName,
            @RequestParam("visionName") String visionName
    ) throws IOException {
        String subjectImgUrl = s3ImageUploadService.uploadFile(subjectImage, s3PathBuildService.buildPathForItem("subject"));
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectItemCreateService.createSubjectItem(itemPrice, subjectName, subjectImgUrl, modelName, visionName));
    }

    @GetMapping
    public ResponseEntity getAllSubjectItems(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectItemFindDao.getSubjectItemListByPage(pageable));
    }
}
