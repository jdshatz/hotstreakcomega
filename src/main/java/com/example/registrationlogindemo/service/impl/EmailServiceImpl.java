package com.example.registrationlogindemo.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import com.example.registrationlogindemo.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.registrationlogindemo.model.Mail;

@Service
public class EmailServiceImpl implements EmailService
{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Mail mail)
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(String.valueOf(new InternetAddress(mail.getMailFrom())));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        }
        catch (MessagingException | jakarta.mail.MessagingException e) {
            e.printStackTrace();
        }
    }

}