package com.siliconvalley.record;

import com.siliconvalley.domain.google.service.VisionDetectingService;
import com.siliconvalley.domain.point.application.StageRewardApp;
import com.siliconvalley.domain.profile.dao.ProfileFindDao;
import com.siliconvalley.domain.profile.domain.Profile;
import com.siliconvalley.domain.record.application.RecordCreateService;
import com.siliconvalley.domain.record.dao.RecordFindDao;
import com.siliconvalley.domain.record.dao.RecordRepository;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.dto.RecordCreateRequest;
import com.siliconvalley.domain.record.exception.RecordAlreadyExist;
import com.siliconvalley.domain.stage.dao.StageFindDao;
import com.siliconvalley.domain.stage.domain.Score;
import com.siliconvalley.domain.stage.domain.Stage;
import com.siliconvalley.global.common.dto.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("기록 생성 테스트")
public class RecordCreateTest {

    @InjectMocks
    RecordCreateService recordCreateService;

    @Mock RecordRepository recordRepository;
    @Mock RecordFindDao recordFindDao;
    @Mock ProfileFindDao profileFindDao;
    @Mock StageFindDao stageFindDao;
    @Mock StageRewardApp stageRewardApp;
    @Mock VisionDetectingService visionDetectingService;

    Long profileId;
    Long stageId;
    Profile mockProfile;
    Stage mockStage;
    Record mockRecord;
    RecordCreateRequest dto;


    @BeforeEach
    void setUp() {
        profileId = 1L;
        stageId = 1L;
        mockProfile = mock(Profile.class);
        mockStage = mock(Stage.class);
        dto = mock(RecordCreateRequest.class);
        mockRecord = mock(Record.class);
        when(profileFindDao.findById(profileId)).thenReturn(mockProfile);
        when(stageFindDao.findById(stageId)).thenReturn(mockStage);

    }


    @Test
    @DisplayName("기록이 존재할때 발생하는 예외")
    void 이미_기록이_존재할때_발생하는_예외() {
        when(recordFindDao.findByProfileId(profileId)).thenReturn(Optional.of(mockRecord));

        // When & Then
        assertThrows(RecordAlreadyExist.class, () -> {
            recordCreateService.evaluateCanvasAndcreateRecord(profileId, stageId, dto);
        });
    }

    @Test
    @DisplayName("새로운 기록 생성 성공")
    void 기록_생성_성공() {
        // Given
        when(recordFindDao.findByProfileId(profileId)).thenReturn(Optional.empty());
        Long canvasId = 1L;
        when(dto.getCanvasId()).thenReturn(canvasId);
        when(visionDetectingService.calculateCanvasScore(canvasId)).thenReturn(Score.HIGH);
        when(mockStage.getPoint()).thenReturn(100);

        // when
        Response response = recordCreateService.evaluateCanvasAndcreateRecord(profileId, stageId, dto);

        // then
        assertEquals(response.getStatus().getCode(), 201);
    }
}
