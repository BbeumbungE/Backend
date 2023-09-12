package com.siliconvalley.domain.rabbitMQ.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueBindingService {
    private final RabbitAdmin rabbitAdmin;
    private final TopicExchange topicExchange;

    public void createQueueAndBinding(String subjectName) {
        String queueName = subjectName + "_queue";
        String routingKey = subjectName + ".*";

        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);

        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
        rabbitAdmin.declareBinding(binding);
    }

}
