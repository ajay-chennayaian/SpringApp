package com.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.Properties;

/*
     Its just a simple program to check with mail service
     not belongs to the project just testing purposes.
 */
public class MailService {

    public static Logger logger = LoggerFactory.getLogger(MailService.class);
    public static void main(String[] args){

        sendMail();
    }

    public static void sendMail() {
        String host = "smtp.ethereal.email";
        String port = "587";
        final String username = "daphney7@ethereal.email";
        final String password = "X7WCvFF2UmyyuUsSpm";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        //creating session for connection

        Session session = Session.getInstance(props, new Authenticator() {
            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse("toanyone"));
            msg.setSubject("Ethereal test");
            msg.setText("This is only captured, not sent");

            Transport.send(msg);
        }catch (Exception e){
            logger.atLevel(Level.ERROR).log(e.getStackTrace().toString());
        }
    }

}
