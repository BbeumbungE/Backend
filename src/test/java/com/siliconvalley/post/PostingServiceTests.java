package com.siliconvalley.post;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import static org.junit.jupiter.api.Assertions.*;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.post.code.PostCode;
import com.siliconvalley.domain.post.dao.PostFindDao;
import com.siliconvalley.domain.post.dao.PostRepository;
import com.siliconvalley.domain.post.domain.Post;
import com.siliconvalley.domain.post.exception.IllegalDeleteException;
import com.siliconvalley.domain.post.service.PostingService;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.global.common.dto.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName(value = "게시물 CRUD 테스트")
public class PostingServiceTests {

    @InjectMocks
    private PostingService postingService;

    @Mock
    private PostFindDao postFindDao;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CanvasFindDao canvasFindDao;

    @Test
    @DisplayName(value = "그림에 대한 게시물을 작성한다.")
    void 게시물작성() {
        // Given
        Long canvasId = 1L;
        Canvas mockCanvas = mock(Canvas.class);
        Post mockPost = mock(Post.class);
        when(canvasFindDao.findById(canvasId)).thenReturn(mockCanvas);
        when(mockCanvas.buildPost()).thenReturn(mockPost);

        // When
        Response response = postingService.createPostForCanvas(canvasId);

        // Then
        verify(postRepository).save(mockPost);
        assertEquals(PostCode.POSTING_SUCCESS.getCode(), response.getStatus().getCode());
    }

    @Test
    @DisplayName("게시물을 삭제한다.")
    void 게시물삭제() {
        // Given
        Long postId = 1L;
        Profile mockProfile = mock(Profile.class);
        Post mockPost = mock(Post.class);
        when(mockProfile.getId()).thenReturn(postId);
        when(postFindDao.findById(postId)).thenReturn(mockPost);
        when(mockPost.getId()).thenReturn(postId);

        // When
        Response response = postingService.deletePost(postId, mockProfile);

        // Then
        verify(postRepository).delete(mockPost);
        assertEquals(PostCode.DELETE_POST_SUCCESS.getCode(), response.getStatus().getCode());
    }

    @Test
    @DisplayName(value = "다른 유저의 게시물을 삭제한다.")
    void 게시물삭제예외() {
        // Given
        Long postId = 1L;
        Profile mockProfile = mock(Profile.class);
        Post mockPost = mock(Post.class);
        when(mockProfile.getId()).thenReturn(2L);  // Different ID
        when(postFindDao.findById(postId)).thenReturn(mockPost);
        when(mockPost.getId()).thenReturn(postId);

        // Then
        assertThrows(IllegalDeleteException.class, () -> postingService.deletePost(postId, mockProfile));
    }
}
