//package com.siliconvalley.canvas;
//
//import com.siliconvalley.domain.canvas.dto.CanvasConvertResponse;
//import com.siliconvalley.domain.item.subject.domain.Subject;
//import com.siliconvalley.domain.pix2pix.domain.Pix2Pix;
//import com.siliconvalley.domain.post.service.RankCachingService;
//import com.siliconvalley.domain.rabbitMQ.dto.SketchConversionRequest;
//import com.siliconvalley.domain.rabbitMQ.dto.SketchConversionResponse;
//import com.siliconvalley.domain.rabbitMQ.service.ConvertRequestSender;
//import com.siliconvalley.domain.rabbitMQ.service.ConvertResponseReceiver;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//@DisplayName("그림 변환 테스트")
//public class CanvasConvertTests {
//
//    @InjectMocks
//    private ConvertRequestSender convertRequestSender;
//    @InjectMocks
//    private ConvertResponseReceiver convertResponseReceiver;
//    @Mock
//    private RabbitTemplate rabbitTemplate;
//    @Mock
//    private RankCachingService rankCachingService;  // Redis 서비스 모킹
//
//    @Test
//    @DisplayName(value = "변환 요청을 알맞은 메시지 큐로 전달한다.")
//    void 변환_메시지_전달(){
//
//        // given
//        String sketchUrl = "testSketchUrl";
//        Long canvasId = 1L;
//        Long profileId = 1L;
//        Subject mockSubject = mock(Subject.class);
//
//        Pix2Pix mockPix2Pix = mock(Pix2Pix.class);
//        when(mockPix2Pix.getModelName()).thenReturn("testModelName");
//
//        // mockSubject의 getPix2Pix()가 mockPix2Pix를 반환하도록 설정합니다.
//        when(mockSubject.getPix2Pix()).thenReturn(mockPix2Pix);
//
//        // Redis 서비스 호출에 대한 모킹
//        when(rankCachingService.getTopPostThisWeek(anyLong())).thenReturn("topPost1");
//
//        // when
//        convertRequestSender.sendSketchConversionRequest(sketchUrl, canvasId, profileId, mockSubject);
//
//        // then
//        verify(rabbitTemplate).convertAndSend(anyString(), eq("sketch_conversion_request_queue"), any(SketchConversionRequest.class));
//        verify(rankCachingService).getTopPostThisWeek(anyLong());
//    }
//
//
//    @Test
//    @DisplayName(value = "메시지 큐에서 변환 응답을 수신하고 결과를 클라이언트에게 전송한다.")
//    void 변환_메시지_수신(){
//
//    }
//
//    @Test
//    @DisplayName(value = "데모 변환 요청을 알맞은 메시지 큐로 전달한다.")
//    void 데모_변환_메시지_전달(){
//
//    }
//
//    @Test
//    @DisplayName(value = "메시지 큐에서 데모 변환 응답을 수신하고 결과를 클라이언트에게 전송한다.")
//    void 데모_변환_메시지_수신(){
//
//    }
//
//    @Test
//    @DisplayName(value = "메시지 큐에서 변환 응답을 수신하고, 메시지 처리 과정에 오류가 발생하면 메시지를 DLQ로 반송한다.")
//    void 변환_메시지_처리_오류(){
//
//    }
//
//    @Test
//    @DisplayName(value = "메시지 큐에서 데모 변환 응답을 수신하고, 메시지 처리 과정에 오류가 발생하면 메시지를 DLQ로 반송한다.")
//    void 데모_변환_메시지_처리_오류(){
//
//    }
//}
//
