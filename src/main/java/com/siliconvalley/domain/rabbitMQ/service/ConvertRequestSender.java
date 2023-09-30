package com.siliconvalley.domain.rabbitMQ.service;

import com.siliconvalley.domain.canvas.dto.CanvasConvertResponse;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.post.service.RankCachingService;
import com.siliconvalley.domain.rabbitMQ.code.RabbitMQCode;
import com.siliconvalley.domain.rabbitMQ.dto.DemoConversionRequest;
import com.siliconvalley.domain.rabbitMQ.dto.SketchConversionRequest;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConvertRequestSender {

    private final RabbitTemplate rabbitTemplate;
    private final RankCachingService rankCachingService;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    public Response sendSketchConversionRequest(String sketchUrl, Long canvasId, Long profileId, Subject subject) {
        SketchConversionRequest request = new SketchConversionRequest(sketchUrl, canvasId, profileId, subject.getPix2Pix().getModelName());
        rabbitTemplate.convertAndSend(exchange, "sketch_conversion_request_queue" , request);
        return Response.of(RabbitMQCode.CONVERSION_REQUEST_SUCCESS, new CanvasConvertResponse(canvasId, rankCachingService.getTopPostThisWeek(subject.getId())));
    }

    public Response sendDemoConversionRequest(String sketchUrl, String tempId, String modelName){
        DemoConversionRequest request = new DemoConversionRequest(sketchUrl, modelName, tempId);
        rabbitTemplate.convertAndSend(exchange, "demo_conversion_request_queue", request);
        return Response.of(RabbitMQCode.CONVERSION_REQUEST_SUCCESS);
    }
}
