package com.siliconvalley.domain.rabbitMQ.service;

import com.siliconvalley.domain.canvas.service.CanvasConvertService;
import com.siliconvalley.domain.rabbitMQ.dto.SketchConversionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConvertResponseReceiver {

    private final CanvasConvertService canvasConvertService;

    @RabbitListener(queues = "sketch_conversion_response_queue")
    public void receiveMessage(SketchConversionResponse response){
        log.info("Received message: {}", response);
        canvasConvertService.updateConvertedData(response);
    }
}
