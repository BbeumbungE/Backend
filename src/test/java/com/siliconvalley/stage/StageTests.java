package com.siliconvalley.stage;

import com.siliconvalley.domain.stage.dao.StageFindDao;
import com.siliconvalley.domain.stage.dao.StageRepository;
import com.siliconvalley.domain.stage.domain.Stage;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Stage 조회 테스트")
public class StageTests {

    @InjectMocks
    private StageFindDao stageFindDao;

    @Mock
    private StageRepository stageRepository;

    @Test
    @DisplayName(value = "조회된 스테이지가 3개인 경우")
    void 스테이지_조회_세개() {
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
    @DisplayName(value = "조회된 스테이지가 3개 미만인 경우 3개의 응답을 제공한다.")
    void 스테이지_조회_세개미만() {
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
}
