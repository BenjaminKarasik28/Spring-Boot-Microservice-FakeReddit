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

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest(SmtpServerService.class)
public class SmtpServerServiceTest {


    private String ok;
    private String success;


    @InjectMocks
    private SmtpServerService smtpServerService;



    @Before
    public void init(){
        ok = "ok";
        success = "Email sent successfully.";
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
//        String target = "email";
//
//        mockStatic(System.class);
//        Session session = null;
//
//        assert session != null;
//        expect(session.getTransport()).andReturn(transport);
//        assertNotNull(String.valueOf(transport), new SmtpServerServiceTest());
//
//
////        String success2 = smtpServerService.send(target);
////        assertEquals(success2, success);

    //}


}
