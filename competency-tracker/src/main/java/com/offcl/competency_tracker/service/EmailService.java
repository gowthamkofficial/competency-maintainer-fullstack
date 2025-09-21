package com.offcl.competency_tracker.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendRegistrationEmail(String to, String name, String email, String password) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("email", email);
        context.setVariable("password", password);

        String htmlContent = templateEngine.process("registration-email", context);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(htmlContent, true);
        helper.setTo(to);
        helper.setSubject("Welcome to Competency Management System");
        helper.setFrom("gowthamk.techzone@gmail.com");

        mailSender.send(mimeMessage);
    }
}
