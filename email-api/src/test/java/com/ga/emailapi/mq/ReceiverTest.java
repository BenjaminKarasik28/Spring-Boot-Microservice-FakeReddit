package com.ga.emailapi.mq;

import com.ga.emailapi.service.SmtpServerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.mail.MessagingException;
import static org.junit.Assert.*;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReceiverTest {

    @Mock
    SmtpServerService smtpServerService;

    @InjectMocks
    Receiver receiver;

    @Test
    public void receive_Receiver_Success() throws MessagingException {
        when(smtpServerService.setProps()).thenReturn("ok");
        when(smtpServerService.send(anyString())).thenReturn("Email sent successfully.");
        receiver.receive("Message");
    }

}
