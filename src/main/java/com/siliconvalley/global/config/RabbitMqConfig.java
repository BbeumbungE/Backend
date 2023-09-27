package com.siliconvalley.global.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    Queue requestQueue() {
        return new Queue("sketch_conversion_request_queue", true);
    }

    @Bean
    Queue demoConversionRequestQueue() {return new Queue("demo_conversion_request_queue", true);}

    @Bean
    Queue responseQueue() {
        return QueueBuilder.durable("sketch_conversion_response_queue")
                .withArgument("x-dead-letter-exchange", "sketch_conversion_dead_letter_exchange")
                .withArgument("x-dead-letter-routing-key", "sketch_conversion_dead_letter_queue")
                .build();
    }

    @Bean
    Queue demoConversionResponseQueue() {
        return QueueBuilder.durable("demo_conversion_response_queue")
                .withArgument("x-dead-letter-exchange", "sketch_conversion_dead_letter_exchange")
                .withArgument("x-dead-letter-routing-key", "sketch_conversion_dead_letter_queue")
                .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return new Queue("sketch_conversion_dead_letter_queue", true);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("sketch_conversion_exchange");
    }

    @Bean
    Exchange deadLetterExchange() {
        return ExchangeBuilder.topicExchange("sketch_conversion_dead_letter_exchange").durable(true).build();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding bindingRequestQueue(Queue requestQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(requestQueue).to(topicExchange).with("sketch_conversion_request_queue");
    }

    @Bean
    public Binding bindingDemoRequestQueue(Queue demoConversionRequestQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(demoConversionRequestQueue).to(topicExchange).with("demo_conversion_request_queue");
    }

    @Bean
    public Binding bindingResponseQueue(Queue responseQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(responseQueue).to(topicExchange).with("sketch_conversion_response_queue");
    }

    @Bean
    public Binding bindingDemoResponseQueue(Queue demoConversionResponseQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(demoConversionResponseQueue).to(topicExchange).with("demo_conversion_response_queue");
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with("sketch_conversion_dead_letter_queue")
                .noargs();
    }

}
