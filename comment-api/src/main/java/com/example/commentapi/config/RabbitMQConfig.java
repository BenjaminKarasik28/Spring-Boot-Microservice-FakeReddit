package com.example.commentapi.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RabbitMQConfig {

    private final static String QUEUE_NAME = "queue1";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false, false, false);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}