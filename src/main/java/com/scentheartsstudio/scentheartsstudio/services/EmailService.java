package com.scentheartsstudio.scentheartsstudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.scentheartsstudio.scentheartsstudio.utils.CustomException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender jms;

    // Mengambil value dari application properties
    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(
            String recipient,
            String subject,
            String msgBody) throws CustomException {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(sender);
            mail.setSubject(subject);
            mail.setTo(recipient);
            mail.setText(msgBody);
            jms.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(452, "Email Failed to send!!");
        }
    }
}
