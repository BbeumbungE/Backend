package com.siliconvalley.vision;

import com.siliconvalley.domain.canvas.dao.CanvasFindDao;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.google.service.GoogleVisionApiService;
import com.siliconvalley.domain.google.service.VisionDetectingService;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.pix2pix.domain.Pix2Pix;
import com.siliconvalley.domain.stage.domain.Score;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@Slf4j
@DisplayName(value = "점수 측정 테스트")
public class VisionDetectingServiceTests {

    @InjectMocks
    private VisionDetectingService visionDetectingService;

    @Mock
    private GoogleVisionApiService visionService;

    @Mock
    private CanvasFindDao canvasFindDao;


    @Test
    @DisplayName(value = "다른 물체만 감지되었을 때 그림의 점수를 LOW(1)로 반환")
    void 그림점수_낮음() {
        // Given
        Long canvasId = 1L;
        Canvas mockCanvas = mock(Canvas.class, RETURNS_DEEP_STUBS);
        when(canvasFindDao.findById(canvasId)).thenReturn(mockCanvas);
        when(mockCanvas.getCanvas()).thenReturn("sampleCanvasData");
        when(visionService.detectLabels("sampleCanvasData")).thenReturn(Map.of("anotherModelName", 0.8f)); // 수정된 점수
        when(mockCanvas.getSubject().getPix2Pix().getVisionName()).thenReturn("sampleModelName");

        // When
        Score result = visionDetectingService.calculateCanvasScore(canvasId);

        // Then
        assertEquals(Score.LOW, result);
    }

    @Test
    @DisplayName(value = "목표 물체가 감지 되었을 때 그림의 점수를 MEDIUM(2)로 반환")
    void 그림점수_중간() {
        // Given
        Long canvasId = 1L;
        Canvas mockCanvas = mock(Canvas.class, RETURNS_DEEP_STUBS);
        when(canvasFindDao.findById(canvasId)).thenReturn(mockCanvas);
        when(mockCanvas.getCanvas()).thenReturn("sampleCanvasData");
        when(visionService.detectLabels("sampleCanvasData")).thenReturn(Map.of("sampleModelName", 0.3f)); // 수정된 점수
        when(mockCanvas.getSubject().getPix2Pix().getVisionName()).thenReturn("sampleModelName");

        // When
        Score result = visionDetectingService.calculateCanvasScore(canvasId);

        // Then
        assertEquals(Score.MEDIUM, result);
    }

    @Test
    @DisplayName(value = "목표 물체 감지율 80%이상일 때 그림의 점수를 HIGH(3)으로 반환")
    void 그림점수_높음() {
        // Given
        Long canvasId = 1L;
        Canvas mockCanvas = mock(Canvas.class, RETURNS_DEEP_STUBS);
        when(canvasFindDao.findById(canvasId)).thenReturn(mockCanvas);
        when(mockCanvas.getCanvas()).thenReturn("sampleCanvasData");
        when(visionService.detectLabels("sampleCanvasData")).thenReturn(Map.of("sampleModelName", 0.8f)); // 수정된 점수
        when(mockCanvas.getSubject().getPix2Pix().getVisionName()).thenReturn("sampleModelName");

        // When
        Score result = visionDetectingService.calculateCanvasScore(canvasId);

        // Then
        assertEquals(Score.HIGH, result);
    }

    @Test
    @DisplayName(value = "감지된 물체가 없을 경우 그림의 점수를 LOW(1)로 반환")
    void 물체감지실패_점수낮음() {
        // Given
        Long canvasId = 1L;
        Canvas mockCanvas = mock(Canvas.class, RETURNS_DEEP_STUBS);
        when(canvasFindDao.findById(canvasId)).thenReturn(mockCanvas);
        when(mockCanvas.getCanvas()).thenReturn("sampleCanvasData");

        // 탐지된 물체가 없을 경우 빈 Map 반환
        when(visionService.detectLabels("sampleCanvasData")).thenReturn(Map.of());

        // When
        Score result = visionDetectingService.calculateCanvasScore(canvasId);

        // Then
        assertEquals(Score.LOW, result);
    }

    @Test
    @DisplayName(value = "입출력 오류가 발생했을 경우 그림의 점수를 LOW(1)로 반환")
    void 입출력오류_점수낮음() {
        // Given
        Long canvasId = 1L;
        Canvas mockCanvas = mock(Canvas.class, RETURNS_DEEP_STUBS);
        when(canvasFindDao.findById(canvasId)).thenReturn(mockCanvas);
        when(mockCanvas.getCanvas()).thenReturn("sampleCanvasData");

        // 탐지된 물체가 없을 경우 빈 Map 반환
        when(visionService.detectLabels("sampleCanvasData")).thenReturn(Map.of("Error", -1f));

        // When
        Score result = visionDetectingService.calculateCanvasScore(canvasId);

        // Then
        assertEquals(Score.LOW, result);
    }
}
