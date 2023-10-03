package com.siliconvalley.domain.post.controller;

import com.siliconvalley.domain.post.code.RankingCode;
import com.siliconvalley.domain.post.dao.PostFindDao;
import com.siliconvalley.domain.post.service.*;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostFindDao postFindDao;
    private final PostEmoteService postEmoteService;
    private final PostDetailService postDetailService;
    private final RankCachingService rankCachingService;
    private final PostingService postingService;
    private final UpdateRankService postRankingService;

    @PostMapping("/canvases/{canvasId}/posts")
    public ResponseEntity<Response> postForCanvas(
            @PathVariable(name = "canvasId") Long canvasId
    ){
        Response response = postingService.createPostForCanvas(canvasId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/posts")
    public ResponseEntity<Response> getAllPosts(
            @RequestParam int page,
            @RequestParam int size
    ){
        Response response = postFindDao.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/subjects/{subjectId}/posts/ranking")
    public ResponseEntity<Response> getRankingThisWeek(
            @PathVariable Long subjectId
    ){
        Response response = rankCachingService.getRankingThisWeekBySubject(subjectId);
        return ResponseEntity.ok(response);
    }

    // 랭킹 수동 업데이트
    @PostMapping("/posts/ranking")
    public ResponseEntity<Response> updateRankingManually(){
        postRankingService.updateRanking();
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.of(RankingCode.UPDATE_RANKING));
    }

    @GetMapping("/subjects/{subjectId}/posts")
    public ResponseEntity<Response> getPostsBySubject(
            @PathVariable Long subjectId,
            @RequestParam int page,
            @RequestParam int size){
        Response response = postFindDao.getPostsBySubjectName(subjectId, PageRequest.of(page, size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Response> getPostDetail(
            @PathVariable Long postId,
            @RequestParam Long profileId){
        Response response = postDetailService.getPostDetail(postId, profileId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/posts/{postId}/profiles/{profileId}/emotions")
    public ResponseEntity<Response> emoteToPost(
            @PathVariable Long postId,
            @PathVariable Long profileId,
            @RequestParam Long emotionTypeId
    ){
        Response response = postEmoteService.emoteToPost(postId, emotionTypeId, profileId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/posts/{postId}/profiles/{profileId}/emotions")
    public ResponseEntity<Response> updateEmotionFromPost(
            @PathVariable Long postId,
            @PathVariable Long profileId,
            @RequestParam Long emotionTypeId
    ){
        Response response = postEmoteService.updateEmote(postId, profileId, emotionTypeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @DeleteMapping("/posts/{postId}/profiles/{profileId}/emotions")
    public ResponseEntity<Response> cancelEmotionFromPost(
            @PathVariable Long postId,
            @PathVariable Long profileId
    ){
        Response response = postEmoteService.cancelEmote(postId, profileId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
