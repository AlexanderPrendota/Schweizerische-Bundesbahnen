package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by aleksandrprendota on 01.04.17.
 *
 * Service for sending message to email
 */

@Component
public class MailService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * send a message withouth attachment
     * @param mail from Mail modele
     * @throws MessagingException
     */
    public void send(Mail mail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(mail.getSubject());
        helper.setTo(mail.getTo());
        helper.setText(mail.getText(), true);
        javaMailSender.send(message);

    }

    /**
     * send a message with attachment
     * @param mail from Mail modele
     * @param file File for attach
     * @throws MessagingException
     */
    public void sendTicket(Mail mail, File file) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(mail.getSubject());
        helper.setTo(mail.getTo());
        helper.setText(mail.getText(), true);
        helper.addAttachment("Your ticket", file);
        javaMailSender.send(message);
    }



}
