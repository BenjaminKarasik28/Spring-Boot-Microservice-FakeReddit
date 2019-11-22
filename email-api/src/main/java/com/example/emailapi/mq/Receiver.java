package com.example.emailapi.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
@Service
@RabbitListener(queues = "queue1")
public class Receiver {
    @RabbitHandler
    public void receive(String msg){
        System.out.println(msg);
    }
}