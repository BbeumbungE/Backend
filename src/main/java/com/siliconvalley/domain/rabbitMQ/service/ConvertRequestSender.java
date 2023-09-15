package com.siliconvalley.domain.rabbitMQ.service;

import com.siliconvalley.domain.canvas.dto.CanvasConvertResponse;
import com.siliconvalley.domain.item.subject.dao.SubjectFindDao;
import com.siliconvalley.domain.item.subject.domain.Subject;
import com.siliconvalley.domain.post.service.RankCachingService;
import com.siliconvalley.domain.rabbitMQ.code.RabbitMQCode;
import com.siliconvalley.domain.rabbitMQ.dto.SketchConversionRequest;
import com.siliconvalley.global.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertRequestSender {

    private final RabbitTemplate rabbitTemplate;
    private final RankCachingService rankCachingService;
    private final SubjectFindDao subjectFindDao;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    public Response sendSketchConversionRequest(String sketchUrl, Long canvasId, Long profileId, Long subjectId) {
        String subjectName = subjectFindDao.findById(subjectId).getSubjectName();
        SketchConversionRequest request = new SketchConversionRequest(sketchUrl, canvasId, profileId, subjectName);
        rabbitTemplate.convertAndSend(exchange, "sketch_conversion_request_queue" , request);
        return Response.of(RabbitMQCode.CONVERSION_REQUEST_SUCCESS, new CanvasConvertResponse(canvasId, rankCachingService.getTopPostThisWeek()));
    }
}
