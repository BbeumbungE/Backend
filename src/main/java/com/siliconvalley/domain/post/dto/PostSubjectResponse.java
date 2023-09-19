package com.siliconvalley.domain.post.dto;

import com.siliconvalley.domain.item.subject.domain.Subject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSubjectResponse {
    private Long subjectId;
    private String subjectName;
    private String subjectImage;
    private List<PostListResponse> postLists;
    private int currentPage;
    private int totalPages;

    public PostSubjectResponse(Subject subject, Page<PostListResponse> postPage){
        this.subjectId = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.subjectImage = subject.getSubjectImage();
        this.postLists = postPage.getContent();
        this.currentPage = postPage.getNumber();
        this.totalPages = postPage.getTotalPages();
    }
}