package com.siliconvalley.stage;

import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.record.dao.RecordFindDao;
import com.siliconvalley.domain.record.domain.Record;
import com.siliconvalley.domain.record.dto.RecordResponseWithHighestClearedStageNumber;
import com.siliconvalley.domain.stage.dao.StageFindDao;
import com.siliconvalley.domain.stage.dao.StageRepository;
import com.siliconvalley.domain.stage.domain.Stage;
import com.siliconvalley.domain.stage.dto.StageWithRecordResponse;
import com.siliconvalley.global.common.dto.Response;
import com.siliconvalley.global.common.dto.page.PageResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Stage 조회 테스트")
public class StageTests {

    @InjectMocks
    private StageFindDao stageFindDao;

    @Mock
    private StageRepository stageRepository;
    @Mock
    private RecordFindDao recordFindDao;

    @Test
    @DisplayName(value = "관리자용 스테이지 조회에서 응답이 3개인 경우")
    void 관리자_스테이지_조회_세개() {
        // given
        Stage mockStage1 = Mockito.mock(Stage.class);
        Stage mockStage2 = Mockito.mock(Stage.class);
        Stage mockStage3 = Mockito.mock(Stage.class);
        List<Stage> stages = Arrays.asList(mockStage1, mockStage2, mockStage3);
        Page<Stage> stagePage = new PageImpl<>(stages);
        when(stageRepository.findAll(Mockito.any(Pageable.class))).thenReturn(stagePage);

        // when
        Response response = stageFindDao.getAllStage(PageRequest.of(0, 3));

        // then
        assertNotNull(response);
        PageResponse pageResponse = (PageResponse) response.getContent();
        assertEquals(3, ((List<?>) pageResponse.getData()).size());
    }

    @Test
    @DisplayName(value = "관리자용 스테이지 조회에서 조회된 스테이지가 3개 미만인 경우 3개의 응답을 제공한다.")
    void 관리자_스테이지_조회_세개미만() {
        // given
        Stage mockStage1 = Mockito.mock(Stage.class);
        Stage mockStage2 = Mockito.mock(Stage.class);
        List<Stage> stages = Arrays.asList(mockStage1, mockStage2);
        Page<Stage> stagePage = new PageImpl<>(stages);
        when(stageRepository.findAll(Mockito.any(Pageable.class))).thenReturn(stagePage);

        // when
        Response response = stageFindDao.getAllStage(PageRequest.of(0, 3));

        // then
        assertNotNull(response);
        PageResponse pageResponse = (PageResponse) response.getContent();
        assertEquals(3, ((List<?>) pageResponse.getData()).size());
    }

    @Test
    @DisplayName(value = "사용자용 스테이지 조회에서 조회된 스테이지가 3개인 경우")
    void 스테이지_조회_세개(){

        // given
        Long profileId = 1L;

        Stage mockStage1 = Mockito.mock(Stage.class);
        Stage mockStage2 = Mockito.mock(Stage.class);
        Stage mockStage3 = Mockito.mock(Stage.class);

        Subject mockSubject1 = Mockito.mock(Subject.class);
        Subject mockSubject2 = Mockito.mock(Subject.class);
        Subject mockSubject3 = Mockito.mock(Subject.class);

        when(mockStage1.getSubject()).thenReturn(mockSubject1);
        when(mockStage2.getSubject()).thenReturn(mockSubject2);
        when(mockStage3.getSubject()).thenReturn(mockSubject3);

        when(mockStage1.getStageNum()).thenReturn(1);
        when(mockStage2.getStageNum()).thenReturn(2);
        when(mockStage3.getStageNum()).thenReturn(3);

        List<Stage> stages = Arrays.asList(mockStage1, mockStage2, mockStage3);
        Page<Stage> stagePage = new PageImpl<>(stages);
        when(stageRepository.findAll(Mockito.any(Pageable.class))).thenReturn(stagePage);

        // Mock the records associated with stages.
        when(recordFindDao.findByProfileIdAndStageId(profileId, mockStage1.getId())).thenReturn(Optional.empty());
        when(recordFindDao.findByProfileIdAndStageId(profileId, mockStage2.getId())).thenReturn(Optional.empty());
        when(recordFindDao.findByProfileIdAndStageId(profileId, mockStage3.getId())).thenReturn(Optional.empty());

        // when
        Response response = stageFindDao.getAllStageWithRecord(profileId, PageRequest.of(0, 3));

        // then
        assertNotNull(response);
        Object content = response.getContent();
        assertTrue(content instanceof PageResponse);

        PageResponse pageResponse = (PageResponse) content;
        Object data = pageResponse.getData();
        assertTrue(data instanceof RecordResponseWithHighestClearedStageNumber);

        RecordResponseWithHighestClearedStageNumber recordResponse = (RecordResponseWithHighestClearedStageNumber) data;
        List<StageWithRecordResponse> stageWithRecordList = recordResponse.getRecord();

        assertEquals(3, stageWithRecordList.size());
    }

    @Test
    @DisplayName(value = "사용자용 스테이지 조회에서 조회된 스테이지가 3개 미만인 경우 3개를 채워서 응답")
    void 스테이지_조회_세개미만(){
        // given
        Long profileId = 1L;
        int pageSize = 3;

        Stage mockStage1 = Mockito.mock(Stage.class);
        Stage mockStage2 = Mockito.mock(Stage.class);

        Record mockRecord1 = Mockito.mock(Record.class);
        Record mockRecord2 = Mockito.mock(Record.class);

        Subject mockSubject1 = Mockito.mock(Subject.class);
        Subject mockSubject2 = Mockito.mock(Subject.class);

        when(mockStage1.getSubject()).thenReturn(mockSubject1);
        when(mockStage2.getSubject()).thenReturn(mockSubject2);

        when(mockStage1.getStageNum()).thenReturn(1);
        when(mockStage2.getStageNum()).thenReturn(2);

        List<Stage> stages = Arrays.asList(mockStage1, mockStage2);
        Page<Stage> stagePage = new PageImpl<>(stages);
        when(stageRepository.findAll(Mockito.any(Pageable.class))).thenReturn(stagePage);

        when(recordFindDao.findByProfileIdAndStageId(profileId, mockStage1.getId())).thenReturn(Optional.of(mockRecord1));
        when(recordFindDao.findByProfileIdAndStageId(profileId, mockStage2.getId())).thenReturn(Optional.of(mockRecord2));

        // when
        Response response = stageFindDao.getAllStageWithRecord(profileId, PageRequest.of(0, pageSize));

        // then
        assertNotNull(response);
        Object content = response.getContent();
        assertTrue(content instanceof PageResponse);

        PageResponse pageResponse = (PageResponse) content;
        Object data = pageResponse.getData();
        assertTrue(data instanceof RecordResponseWithHighestClearedStageNumber);

        RecordResponseWithHighestClearedStageNumber recordResponse = (RecordResponseWithHighestClearedStageNumber) data;
        List<StageWithRecordResponse> stageWithRecordList = recordResponse.getRecord();

        assertEquals(3, stageWithRecordList.size());
        assertEquals(3, stageWithRecordList.get(pageSize-1).getStageNum());
    }
}
