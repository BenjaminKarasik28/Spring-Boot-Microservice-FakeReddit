package com.ga.emailapi.mq;

import com.ga.emailapi.service.SmtpServerService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@RabbitListener(queues = "queue1")
public class Receiver {

    @Autowired
    SmtpServerService smtpServerService;

    @RabbitHandler
    public void receive(String msg) throws MessagingException {
        String s = smtpServerService.setProps();
        String x = smtpServerService.send(msg);

    }

}