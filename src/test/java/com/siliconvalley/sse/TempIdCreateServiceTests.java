package com.siliconvalley.sse;

import com.siliconvalley.domain.sse.application.TempIdCreateService;
import com.siliconvalley.domain.sse.application.CanvasSseEmitterFinder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TempId 생성 테스트")
public class TempIdCreateServiceTests {

    @InjectMocks
    private TempIdCreateService tempIdCreateService;

    @Mock
    private CanvasSseEmitterFinder canvasSseEmitterFinder;

    @Test
    @DisplayName("중복된 TempId가 있을 경우 새로운 TempId 생성")
    public void 중복된_TempId_생성_테스트() {
        // given
        given(canvasSseEmitterFinder.findByTempId(anyString())).willReturn(new SseEmitter(), null);

        // when
        String resultId = tempIdCreateService.createTempIdForSse();

        // then
        assertNotNull(resultId);
    }




    @Test
    @DisplayName("중복된 TempId가 없을 경우 TempId 반환")
    void 중복되지_않은_TempId_생성_테스트() {
        // given
        when(canvasSseEmitterFinder.findByTempId(anyString())).thenReturn(null);

        // when
        String tempId = tempIdCreateService.createTempIdForSse();

        // then
        assertNotNull(tempId);
    }
}
