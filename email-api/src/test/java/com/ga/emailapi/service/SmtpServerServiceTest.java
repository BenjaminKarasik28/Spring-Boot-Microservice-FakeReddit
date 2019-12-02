package com.ga.emailapi.service;
import static org.easymock.EasyMock.*;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.mail.MessagingException;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest(SmtpServerService.class)
public class SmtpServerServiceTest {


    private String ok;
    private String success;
    private Session session;
    private Transport transport;
    private Properties props;


    @InjectMocks
    private SmtpServerService smtpServerService;



    @Before
    public void init() throws NoSuchProviderException {
        ok = "ok";
        success = "Email sent successfully.";
        props = System.getProperties();
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        session = Session.getDefaultInstance(props, null);
        transport = session.getTransport("smtp");
    }

    @Test
    public void setProps_SmtpServerService_Success(){
        String ok2 = smtpServerService.setProps();
        assertEquals(ok, ok2);
    }
    @Test
    public void draft_SmtpServerServiceTest_Success() throws MessagingException {
        String target = "email";
        MimeMessage emailTarget = smtpServerService.draft(target);
        assertNotNull(emailTarget);
    }

//    @Test
//    public void send_SmtpServerServiceTest_Success() throws MessagingException {
//        String s = smtpServerService.send("targetemail");
//        assertNotNull(s);
//
//
//
//    }


}
