package com.siliconvalley.domain.rabbitMQ.service;

import com.rabbitmq.client.Channel;
import com.siliconvalley.domain.canvas.service.CanvasConvertService;
import com.siliconvalley.domain.rabbitMQ.dto.DemoConversionResponse;
import com.siliconvalley.domain.rabbitMQ.dto.SketchConversionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class ConvertResponseReceiver {

    private final CanvasConvertService canvasConvertService;

    @RabbitListener(queues = "sketch_conversion_response_queue", ackMode = "MANUAL")
    public void receiveMessage(SketchConversionResponse response, Message message, Channel channel) throws IOException {
        log.info("Received message: {}", response);
        try {
            canvasConvertService.updateConvertedData(response);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 메시지 승인
        } catch (Exception e) {
            log.error("Error processing the message: {}", e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false); // 메시지 거부, 글로벌 핸들링
        }
    }

    @RabbitListener(queues = "demo_conversion_response_queue", ackMode = "MANUAL")
    public void receiveMessage(DemoConversionResponse response, Message message, Channel channel) throws IOException {
        log.info("Received message: {}", response);
        try {
            canvasConvertService.sendConvertedDemoCanvas(response);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 메시지 승인
        } catch (Exception e) {
            log.error("Error processing the message: {}", e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false); // 메시지 거부, 글로벌 핸들링
        }
    }

}
