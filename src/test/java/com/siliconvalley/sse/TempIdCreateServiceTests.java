package com.siliconvalley.sse;

import com.siliconvalley.domain.item.subject.dao.SketchFindDao;
import com.siliconvalley.domain.item.subject.domain.Sketch;
import com.siliconvalley.domain.item.subject.exception.SketchNotFoundException;
import com.siliconvalley.domain.sse.application.TempIdCreateService;
import com.siliconvalley.domain.sse.application.CanvasSseEmitterFinder;
import com.siliconvalley.domain.sse.code.SseCode;
import com.siliconvalley.domain.sse.dto.CreateTempIdResponse;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.error.exception.BusinessException;
import com.siliconvalley.global.error.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TempId 생성 테스트")
public class TempIdCreateServiceTests {

    @InjectMocks
    private TempIdCreateService tempIdCreateService;

    @Mock
    private SketchFindDao sketchFindDao;
    @Mock
    private CanvasSseEmitterFinder canvasSseEmitterFinder;

    @Test
    @DisplayName(value = "무작위 ID와 주제에 대한 스케치를 1개 응답한다.")
    void 임시아이디_생성_및_스케치_응답() {
        //given
        Long subjectId = 1L;
        String mockSketchImageUrl = "mockedImageUrl";

        when(canvasSseEmitterFinder.findByTempId(anyString())).thenReturn(null);
        when(sketchFindDao.findSketchBySubjectId(subjectId)).thenReturn(Arrays.asList(new Sketch("image1", null), new Sketch(mockSketchImageUrl, null)));

        //when
        Response response = tempIdCreateService.getSubjectImageAndCreateTempId(subjectId);

        //then
        assertEquals(SseCode.TEMP_ID_GENERATE_SUCCESS.getMessage(), response.getStatus().getMessage());
        CreateTempIdResponse responseData = (CreateTempIdResponse) response.getContent();
        assertEquals(subjectId, responseData.getSubjectId());
        assertEquals(mockSketchImageUrl, responseData.getSubjectSketch());
        assertNotNull(responseData.getTempId());  // UUID가 생성되었는지 확인
    }

    @Test
    @DisplayName(value = "주제 ID에 해당하는 스케치가 없을 경우 ENTITY_NOT_FOUND 에러 핸들링")
    void findSketchBySubjectIdWithNoSketchTest() {
        //given
        Long subjectId = 1L;

        when(sketchFindDao.findSketchBySubjectId(subjectId)).thenThrow(new SketchNotFoundException(subjectId));

        //when
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            sketchFindDao.findSketchBySubjectId(subjectId);
        });

        //then
        assertEquals(ErrorCode.ENTITY_NOT_FOUND, exception.getErrorCode());  // 에러 코드 검증
        assertTrue(exception.getMessage().contains(subjectId + " is not found"));  // 에러 메시지 검증
    }



}
