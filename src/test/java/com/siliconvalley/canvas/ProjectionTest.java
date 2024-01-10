package com.siliconvalley.canvas;

import com.siliconvalley.domain.canvas.dao.CanvasRepository;
import com.siliconvalley.domain.canvas.domain.Canvas;
import com.siliconvalley.domain.canvas.dto.CanvasDto;
import com.siliconvalley.domain.canvas.dto.CanvasListSummary;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
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
        List<CanvasListSummary> canvasListSummaryList = canvasRepository.findByProfileId(profileId, CanvasListSummary.class);

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
        assertThat(canvasDto.getId()).isEqualTo(testCanvasId);
        assertThat(canvasDto.getCanvas()).isEqualTo(testUrl);
    }

    @Test
    @DisplayName("동적 프로젝션 확인")
    void 동적_프로젝션_확인(){
        //given
        Long profileId = 1L;

        //when
        log.info("클래스 DTO");
        List<CanvasDto> canvasDtoList = canvasRepository.findByProfileId(profileId, CanvasDto.class);
        log.info("인터페이스 타입");
        List<CanvasListSummary> canvasListSummaries = canvasRepository.findByProfileId(profileId, CanvasListSummary.class);
        log.info("엔티티 객체 타입");
        List<Canvas> canvasList = canvasRepository.findByProfileId(profileId, Canvas.class);
    }
}
