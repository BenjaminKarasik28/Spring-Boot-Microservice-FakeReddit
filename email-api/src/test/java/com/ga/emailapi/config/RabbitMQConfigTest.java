package com.ga.emailapi.config;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.Queue;

public class RabbitMQConfigTest {

    private Queue queue;

    @Before
    public void init(){
        queue = new Queue("queue1", false, false, false);
    }

    @Test
    public void queueConstructor_Queue_Success(){
        assertEquals("queue1", queue.getName());
        assertFalse(queue.isDurable());
        assertFalse(queue.isExclusive());
        assertFalse(queue.isAutoDelete());


    }
}
