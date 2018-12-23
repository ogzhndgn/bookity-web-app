package org.bookity.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Component
public class EmailSender {
    private final static Logger logger = LoggerFactory.getLogger(EmailSender.class);

    @Autowired
    private JavaMailSender mailSender;

    //This method is async because not to interrupt flow
    @Async
    public void sendWelcomeMail(String mailAddress) {
        logger.info("A welcome mail will be sent to: " + mailAddress);
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mailAddress);
            simpleMailMessage.setSubject("Welcome Bookity!");
            simpleMailMessage.setText("Welcome Bookity! Please enjoy more than 10.000+ books!");
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            logger.error("Error while sending welcome mail to " + mailAddress, e);
        }
    }
}