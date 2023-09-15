package com.siliconvalley.domain.item.item.application;

import com.siliconvalley.domain.image.service.S3ImageUploadService;
import com.siliconvalley.domain.item.item.code.ItemCode;
import com.siliconvalley.domain.item.item.dao.ItemRepository;
import com.siliconvalley.domain.item.item.domain.Item;
import com.siliconvalley.domain.item.item.dto.ItemPostSuccessResponse;
import com.siliconvalley.domain.item.item.dto.SubjectItemCreateRequest;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.notification.application.NotificationPushService;
import com.siliconvalley.domain.notification.domain.NotificationType;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectItemCreateService {

    private final ItemRepository itemRepository;
    private final NotificationPushService notificationPushService;
    private final S3ImageUploadService s3ImageUploadService;

    public Response createSubjectItem(Long itemPrice, String subjectName, String subjectImgUrl, String sketchImgUrl) throws IOException {

        Item item = Item.toEntity(itemPrice);

        // Item과 Subject빌드 및 연관관계 매핑
        item.addSubject(Subject.toEntity(subjectName, subjectImgUrl, sketchImgUrl, item));

        // Item이 저장될 때 Subject 자동 저장
        itemRepository.save(item);

        // 새 아이템 출시 알림
        notificationPushService.pushNotification(item, NotificationType.NEW_SUBJECT);

        return Response.of(ItemCode.CREATE_SUCCESS, new ItemPostSuccessResponse(item));
    }
}
