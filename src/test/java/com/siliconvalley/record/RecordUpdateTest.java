package com.siliconvalley.record;

import com.siliconvalley.domain.google.service.VisionDetectingService;
import com.siliconvalley.domain.point.application.StageRewardApp;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.record.application.RecordUpdateService;
import com.siliconvalley.domain.record.dao.RecordFindDao;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.dto.RecordUpdateRequest;
import com.siliconvalley.domain.stage.domain.Score;
import com.siliconvalley.domain.stage.domain.Stage;
import com.siliconvalley.global.common.dto.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("기록 갱신 테스트")
public class RecordUpdateTest {

    @InjectMocks
    RecordUpdateService recordUpdateService;

    @Mock RecordFindDao recordFindDao;
    @Mock StageRewardApp stageRewardApp;
    @Mock VisionDetectingService visionDetectingService;

    @Test
    @DisplayName("기록 갱신")
    void 기록_갱신_테스트() {
        // Given
        // profile 생성
        Profile profile = Profile.builder().build();

        // stage 생성
        Stage stage = Stage.builder()
                .stageNum(1)
                .point(100)
                .timeLimit(120)
                .build();

        // record 생성
        Record record = Record.builder()
                .score(3)
                .profile(profile)
                .stage(stage)
                .build();

        Long recordId = 1L;
        Long canvasId = 1L;
        RecordUpdateRequest dto = mock(RecordUpdateRequest.class);
        when(recordFindDao.findById(recordId)).thenReturn(record);
        when(dto.getCanvasId()).thenReturn(canvasId);
        when(visionDetectingService.calculateCanvasScore(canvasId)).thenReturn(Score.HIGH);

        // When
        Response response = recordUpdateService.evaluateCanvasAndupdateRecord(recordId, dto);

        // Then
        Assertions.assertEquals(response.getStatus().getCode(), 200);
    }


}
