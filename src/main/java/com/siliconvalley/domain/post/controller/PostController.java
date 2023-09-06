package com.siliconvalley.domain.post.controller;

import com.siliconvalley.domain.post.dao.PostFindDao;
import com.siliconvalley.domain.post.dto.EmotionCreateRequest;
import com.siliconvalley.domain.post.service.PostEmoteService;
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

    @GetMapping("/posts")
    public ResponseEntity<Response> getAllPosts(
            @RequestParam int page,
            @RequestParam int size
    ){
        Response response = postFindDao.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(response);
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
    public ResponseEntity<Response> getPostDetail(@PathVariable Long postId){
        Response response = postFindDao.getPostDetail(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/posts/{postId}/profiles/{profileId}/emotions")
    public ResponseEntity<Response> emoteToPost(
            @PathVariable Long postId,
            @PathVariable Long profileId,
            @RequestBody EmotionCreateRequest emotionCreateRequest
    ){
        Response response = postEmoteService.emoteToPost(postId, emotionCreateRequest.getEmotionTypeId(), profileId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
