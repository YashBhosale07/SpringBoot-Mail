package com.yash.controller;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class SendMail {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/sendSimpleMail")
    public ResponseEntity<?>sendSimpleMail() {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(sender);
                message.setTo("yashbhosale404@gmail.com");
                message.setText("Java Simple Mail");
                message.setSubject("Testing");
                javaMailSender.send(message);
                return new ResponseEntity<>("Mail send ", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error while Sending mail", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    @RequestMapping("/sendMailWithAttachment")
    public ResponseEntity<?>sendMailWithAttachment() {
        try {
            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo("yashbhosale404@gmail.com");
            mimeMessageHelper.setText("Java Simple Mail");
            mimeMessageHelper.setSubject("Testing");
            mimeMessageHelper.addAttachment("resume.pdf",new File("C:\\Users\\YASH\\OneDrive\\Desktop\\YASH BHOSALE.pdf"));
            javaMailSender.send(message);
            return new ResponseEntity<>("Mail send ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while Sending mail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    }


