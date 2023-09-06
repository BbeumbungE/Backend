package com.siliconvalley.domain.post.controller;

import com.siliconvalley.domain.post.dao.PostFindDao;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostFindDao postFindDao;

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
}
