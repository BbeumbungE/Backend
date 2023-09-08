package com.siliconvalley.domain.profile.api;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.service.CanvasDeleteService;
import com.siliconvalley.domain.item.myitem.application.MyItemCreateService;
import com.siliconvalley.domain.item.myitem.code.MyItemCode;
import com.siliconvalley.domain.item.myitem.dao.MyItemFindDao;
import com.siliconvalley.domain.point.application.PointManagementService;
import com.siliconvalley.domain.point.code.PointCode;
import com.siliconvalley.domain.profile.application.ProfileManagementService;
import com.siliconvalley.domain.profile.code.ProfileCode;
import com.siliconvalley.domain.profile.application.ProfilePostingService;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.dto.ProfileCreateOrUpdate;
import com.siliconvalley.domain.profile.dto.ProfileResponse;
import com.siliconvalley.domain.record.application.RecordCreateService;
import com.siliconvalley.domain.record.application.RecordUpdateService;
import com.siliconvalley.domain.record.dto.RecordCreateRequest;
import com.siliconvalley.domain.record.dto.RecordUpdateRequest;
import com.siliconvalley.domain.stage.dao.StageFindDao;
import com.siliconvalley.global.common.code.CommonCode;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileApi {

    private final ProfileFindDao profileFindDao;
    private final ProfileManagementService profileManagementService;
    private final MyItemFindDao myItemFindDao;
    private final MyItemCreateService myItemCreateService;
    private final PointManagementService pointManagementService;
    private final ProfilePostingService profilePostingService;
    private final CanvasFindDao canvasFindDao;
    private final CanvasDeleteService canvasDeleteService;

    //stage
    private final StageFindDao stageFindDao;
    // record
    private final RecordCreateService recordCreateService;
    private final RecordUpdateService recordUpdateService;

    /**
     *
     * Profile Management
     *
     * **/

    @PostMapping
    public ResponseEntity createProfile(
            @RequestBody @Valid final ProfileCreateOrUpdate dto,
            @AuthenticationPrincipal OAuth2User oAuth2User
    ) {
        String memberId = (String) oAuth2User.getAttributes().get("id");
        return ResponseEntity.status(HttpStatus.CREATED).body(profileManagementService.createProfile(memberId, dto));
    }

    @GetMapping("/{profileId}")
    public ResponseEntity getProfile(
            @PathVariable("profileId") Long profileId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(profileFindDao.getProfileById(profileId));
    }

    @PatchMapping("/{profileId}")
    public ResponseEntity updateProfile(
            @PathVariable("profileId") Long profileId,
        @RequestBody @Valid final ProfileCreateOrUpdate dto
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(profileManagementService.updateProfile(profileId, dto));
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity deleteProfile(
        @PathVariable("profileId") Long profileId
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(profileManagementService.deleteProfile(profileId));
    }

    /**
     *
     *  MyItem Management
     *
     * **/

    @GetMapping("/{profileId}/items")
    public ResponseEntity getItemsOfProfile(
            @PathVariable("profileId") Long profileId,
            @RequestParam(value = "category", required = false) String category,
            Pageable pageable
    ) {
        Response response = Response.of(CommonCode.GOOD_REQUEST, myItemFindDao.getMyItemListByPage(profileId, pageable, category));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/{profileId}/items/{itemId}")
    public ResponseEntity purchaseSubjectItem(
            @PathVariable(name = "profileId") Long profileId,
            @PathVariable(name = "itemId") Long itemId,
            @RequestParam(value = "category") String category // subject or avatar
    ) {
        Response response = Response.of(MyItemCode.CREATE_SUCCESS, myItemCreateService.createMyItem(profileId, itemId, category));
        return new ResponseEntity(response, HttpStatus.CREATED);
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
        Response response = Response.of(CommonCode.GOOD_REQUEST, pointManagementService.getPoint(profileId));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PatchMapping("/{profileId}/points/{newPointValue}")
    public ResponseEntity updatePoint(
            @PathVariable(name = "profileId") Long profileId,
            @PathVariable(name = "newPointValue") Long newPointValue
    ) {
        pointManagementService.updatePoint(profileId, newPointValue);
        Response response = Response.of(PointCode.UPDATE_SUCCESS);
        return new ResponseEntity(response, HttpStatus.NO_CONTENT);
    }


    /**
     *
     * Post Management
     *
     * **/

    @PostMapping("/{profileId}/canvases/{canvasId}/posts")
    public ResponseEntity<Response> postCanvas(
            @PathVariable Long profileId,
            @PathVariable Long canvasId
    ){
        Response response = profilePostingService.createPostForProfile(profileId, canvasId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

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

    @PostMapping("/{profileId}/stages/{stageId}/record")
    public ResponseEntity createRecord(
            @PathVariable(name = "profileId") Long profileId,
            @PathVariable(name = "stageId") Long stageId,
            @RequestBody RecordCreateRequest dto
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recordCreateService.createRecord(profileId, stageId, dto));
    }

    @GetMapping("/{profileId}/stages/records")
    public ResponseEntity getAllStageWithRecord(
            @PathVariable(name = "profileId") Long profileId,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(stageFindDao.getAllStageWithRecord(profileId, pageable));
    }

    @PatchMapping("/{profileId}/stages/{stageId}/records/{recordId}")
    public ResponseEntity updateRecord(
            @PathVariable(name = "recordId") Long recordId,
            @RequestBody RecordUpdateRequest dto
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(recordUpdateService.updateRecord(recordId, dto));
    }

}
