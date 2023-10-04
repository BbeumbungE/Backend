package com.siliconvalley.domain.profile.api;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.service.CanvasDeleteService;
import com.siliconvalley.domain.item.item.dao.AvatarItemFindDao;
import com.siliconvalley.domain.item.item.dao.SubjectItemFindDao;
import com.siliconvalley.domain.item.myitem.application.MyItemCreateService;
import com.siliconvalley.domain.item.myitem.dao.MyItemFindDao;
import com.siliconvalley.domain.notification.application.NotificationDeleteService;
import com.siliconvalley.domain.notification.dao.NotificationFindDao;
import com.siliconvalley.domain.point.application.PointManagementService;
import com.siliconvalley.domain.profile.application.ProfileCreateService;
import com.siliconvalley.domain.profile.application.ProfileManagementService;
import com.siliconvalley.domain.profile.application.ProfilePostingService;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.dto.ProfileCreate;
import com.siliconvalley.domain.profile.dto.ProfileItemUpdate;
import com.siliconvalley.domain.profile.dto.ProfileNameUpdate;
import com.siliconvalley.domain.record.application.RecordCreateService;
import com.siliconvalley.domain.record.application.RecordUpdateService;
import com.siliconvalley.domain.record.dto.RecordCreateRequest;
import com.siliconvalley.domain.record.dto.RecordUpdateRequest;
import com.siliconvalley.domain.sse.application.SseEmitterService;
import com.siliconvalley.domain.stage.dao.StageFindDao;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileApi {

    // Profile
    private final ProfileFindDao profileFindDao;
    private final ProfileManagementService profileManagementService;
    private final ProfileCreateService profileCreateService;

    // MyItem
    private final MyItemFindDao myItemFindDao;
    private final MyItemCreateService myItemCreateService;

    // Point
    private final PointManagementService pointManagementService;

    // Post
    private final ProfilePostingService profilePostingService;

    // Canvas
    private final CanvasFindDao canvasFindDao;
    private final CanvasDeleteService canvasDeleteService;

    // Subject
    private final SubjectItemFindDao subjectItemFindDao;
    private final AvatarItemFindDao avatarItemFindDao;

    // Stage
    private final StageFindDao stageFindDao;

    // Record
    private final RecordCreateService recordCreateService;
    private final RecordUpdateService recordUpdateService;

    // Notification
    private final SseEmitterService sseEmitterService;
    private final NotificationFindDao notificationFindDao;
    private final NotificationDeleteService notificationDeleteService;

    /**
     *
     * Profile Management
     *
     * **/

    @PostMapping
    public ResponseEntity createProfile(
            @RequestBody @Valid final ProfileCreate dto,
            @AuthenticationPrincipal OAuth2User oAuth2User
    ) {
        String memberId = (String) oAuth2User.getAttributes().get("id");
        return ResponseEntity.status(HttpStatus.CREATED).body(profileCreateService.createProfile(memberId, dto));
    }

    @GetMapping("/{profileId}")
    public ResponseEntity getProfile(
            @PathVariable("profileId") Long profileId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(profileFindDao.getProfileById(profileId));
    }

    @PatchMapping("/{profileId}/names")
    public ResponseEntity updateProfileName(
            @PathVariable("profileId") Long profileId,
            @RequestBody @Valid final ProfileNameUpdate dto
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(profileManagementService.updateProfileName(profileId, dto));
    }
    @PatchMapping("/{profileId}/profile-items/{profileItemId}")
    public ResponseEntity updateProfileAvatar(
            @PathVariable("profileItemId") Long profileItemId,
            @RequestBody @Valid final ProfileItemUpdate dto
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(profileManagementService.updateProfileAvatar(profileItemId,dto));
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity deleteProfile(
            @PathVariable("profileId") Long profileId
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(profileManagementService.deleteProfile(profileId));
    }

    /**
     *
     *  Item Management
     *
     * **/

    @GetMapping("/{profileId}/my-items")
    public ResponseEntity getMyItemsOfProfile(
            @PathVariable("profileId") Long profileId,
            @RequestParam(value = "category", required = false) String category,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(myItemFindDao.getMyItemListByPage(profileId, pageable, category));
    }
    @GetMapping("/{profileId}/items/subjects")
    public ResponseEntity getAllSubjectItemsOfShop(
            @PathVariable("profileId") Long profileId,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(subjectItemFindDao.getSubjectItemListByPage(profileId, pageable));
    }
    @GetMapping("/{profileId}/items/avatars")
    public ResponseEntity getAllAvatarItemsOfShop(
            @PathVariable("profileId") Long profileId,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(avatarItemFindDao.getAvatarItemListByPage(profileId, pageable));
    }

    @PostMapping("/{profileId}/items/{itemId}")
    public ResponseEntity purchaseSubjectItem(
            @PathVariable(name = "profileId") Long profileId,
            @PathVariable(name = "itemId") Long itemId,
            @RequestParam(value = "category") String category // subject or avatar
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(myItemCreateService.createMyItem(profileId, itemId, category));
    }


    /**
     *
     * Point Management
     *
     * **/

    @GetMapping("/{profileId}/points")
    public ResponseEntity getPoint(
            @PathVariable(name = "profileId") Long profileId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(pointManagementService.getPoint(profileId));
    }

    @PatchMapping("/{profileId}/points/{newPointValue}")
    public ResponseEntity updatePoint(
            @PathVariable(name = "profileId") Long profileId,
            @PathVariable(name = "newPointValue") Long newPointValue
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pointManagementService.updatePoint(profileId, newPointValue));
    }


    /**
     *
     * Post Management
     *
     * **/

    @DeleteMapping("/{profileId}/canvases/{canvasId}/posts")
    public ResponseEntity<Response> deletePost(
            @PathVariable Long profileId,
            @PathVariable Long canvasId
    ){
        Response response = profilePostingService.deletePostForProfile(profileId, canvasId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("/{profileId}/canvases")
    public ResponseEntity<Response> getCanvasList(
            @PathVariable Long profileId,
            @RequestParam int page,
            @RequestParam int size
    ){
        Response response = canvasFindDao.findByProfileId(profileId, PageRequest.of(page, size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{profileId}/canvases/{canvasId}")
    public ResponseEntity<Response> getCanvasDetail(
            @PathVariable Long profileId,
            @PathVariable Long canvasId
    ){
        Response response = canvasFindDao.findCanvasDetail(profileId, canvasId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{profileId}/canvases/{canvasId}")
    public ResponseEntity<Response> deleteCanvas(
            @PathVariable Long profileId,
            @PathVariable Long canvasId
    ){
        Response response = canvasDeleteService.deleteCanvas(profileId, canvasId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    /**
     *
     * Record Management
     *
     * **/

    @GetMapping("/{profileId}/stages")
    public ResponseEntity getAllStageWithRecord(
            @PathVariable(name = "profileId") Long profileId,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(stageFindDao.getAllStageWithRecord(profileId, pageable));
    }

    @GetMapping("/{profileId}/stages/{stageId}")
    public ResponseEntity getStageWithRecord(
            @PathVariable(name = "profileId") Long profileId,
            @PathVariable(name = "stageId") Long stageId) {
        return ResponseEntity.status(HttpStatus.OK).body(stageFindDao.getStageWithRecord(profileId, stageId));
    }


    @PostMapping("/{profileId}/stages/{stageId}/records")
    public ResponseEntity evaluateCanvasAndCreateRecord(
            @PathVariable(name = "profileId") Long profileId,
            @PathVariable(name = "stageId") Long stageId,
            @RequestBody RecordCreateRequest dto
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recordCreateService.evaluateCanvasAndcreateRecord(profileId, stageId, dto));
    }

    @PatchMapping("/{profileId}/stages/{stageId}/records/{recordId}")
    public ResponseEntity evaluateCanvasAndUpdateRecord(
            @PathVariable(name = "recordId") Long recordId,
            @RequestBody RecordUpdateRequest dto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(recordUpdateService.evaluateCanvasAndupdateRecord(recordId, dto));
    }

    /**
     * SSE Connect
     **/
    @GetMapping("/{profileId}/sse/connects")
    public SseEmitter connect(
            @PathVariable(name = "profileId") Long profileId,
            @RequestHeader(value = "Last-Event_ID", required = false, defaultValue = "") String lastEventId
    )
    {
        return sseEmitterService.connect(profileId, lastEventId);
    }

    /**
     * Notification
     **/

    @GetMapping("/{profileId}/notifications")
    public ResponseEntity getNotificationPage(
            @PathVariable(name = "profileId") Long profileId,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(notificationFindDao.getNotificationPage(profileId, pageable));
    }

    @GetMapping("/{profileId}/notifications/{notificationId}")
    public ResponseEntity getNotification(
            @PathVariable(name = "notificationId") Long notificationId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(notificationFindDao.getNotification(notificationId));
    }

    @DeleteMapping("/{profileId}/notifications/{notificationId}")
    public ResponseEntity deleteNotification(
            @PathVariable(name = "notificationId") Long notificationId
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(notificationDeleteService.deleteNotification(notificationId));
    }
}
