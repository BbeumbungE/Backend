package com.siliconvalley.canvas;

import com.siliconvalley.domain.canvas.dao.CanvasRepository;
import com.siliconvalley.domain.canvas.dto.CanvasDto;
import com.siliconvalley.domain.canvas.dto.CanvasListSummary;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProjectionTest {

    @Autowired
    CanvasRepository canvasRepository;

    @Test
    void 오픈_프로젝션_확인(){

        //given
        String testUrl = "test";
        Long testCanvasId = 2L;
        Long profileId = 1L;

        //when
        List<CanvasListSummary> canvasListSummaryList = canvasRepository.findByProfileId(profileId);

        //then
        CanvasListSummary summary = canvasListSummaryList.get(0);
        assertThat(summary.getId()).isEqualTo(testCanvasId);
        assertThat(summary.getCanvas()).isEqualTo(testUrl);

    }

    @Test
    @DisplayName("클래스 기반 프로젝션 테스트")
    void 클래스_기반_프로젝션_확인(){
        //given
        String testUrl = "test";
        Long testCanvasId = 2L;
        Long profileId = 1L;

        //when
        List<CanvasDto> canvasDtoList = canvasRepository.findCanvasByProfileId(profileId);

        //then
        CanvasDto canvasDto = canvasDtoList.get(0);
        assertThat(canvasDto.getCanvasId()).isEqualTo(testCanvasId);
        assertThat(canvasDto.getCanvasUrl()).isEqualTo(testUrl);
    }
}
