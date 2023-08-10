package com.resources.mail;

import com.resources.dto.MailDto;
import com.resources.dto.UserDto;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.*;

import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author Praca
 */
public class Mail {

    @Autowired
    private JavaMailSender mailSender;

    public void send(UserDto user, MailDto mailDto) throws MessagingException, UnsupportedEncodingException {

        Authenticator auth = new MailAuthenticator();
        Session session = Session.getInstance(getProperties(), auth);
        session.setDebug(true);

        String senderName = "Finance App";
        String content = mailDto.getContent();
        
        MimeMessage message = new MimeMessage(session);
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(MailAuthenticator.USERNAME, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(mailDto.getSubject());

        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = mailDto.getSiteUrl() + "/" + mailDto.getDetailedPage() + "?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        message.saveChanges();
        Transport transport = session.getTransport("smtp");

        transport.connect("smtp.gmail.com", MailAuthenticator.USERNAME, MailAuthenticator.PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
    }

    private Properties getProperties() {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.user", MailAuthenticator.USERNAME);
        prop.put("mail.smtp.password", MailAuthenticator.PASSWORD);
        prop.put("mail.smtp.auth", "true");
        return prop;
    }
}
