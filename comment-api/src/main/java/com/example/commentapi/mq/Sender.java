package com.example.commentapi.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Queue queue;

    public void send(String message) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(queue.getName(),message);
        System.out.println("Message sent: " + message + " on q: " + queue.getName());

    }
}