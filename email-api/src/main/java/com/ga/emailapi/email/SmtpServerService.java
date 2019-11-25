package com.ga.emailapi.email;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SmtpServerService {

    Session session;

    public void setProps(){
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        session = Session.getDefaultInstance(emailProperties, null);
    }

    private MimeMessage draft(String targetEmail) throws AddressException, MessagingException {
        String emailSubject = "Notification from FakeReddit";
        String emailBody = "Somebody commented on your post!";
        MimeMessage email = new MimeMessage(session);
        email.setSubject(emailSubject);
        email.setText(emailBody);
        email.addRecipient(Message.RecipientType.TO, new InternetAddress(targetEmail));

        return email;

    }

    public void send(String target) throws AddressException, MessagingException{
        String fromUser = "fake.redditga@gmail.com";
        String emailHost = "smtp.gmail.com";
        Transport transport = session.getTransport("smtp");
        transport.connect(emailHost, fromUser);

        MimeMessage emailMessage = draft(target);

        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }


}
