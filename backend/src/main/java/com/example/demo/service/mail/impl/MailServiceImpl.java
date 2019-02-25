package com.example.demo.service.mail.impl;

import com.example.demo.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: 王轩
 * @Description:
 * @Date: 2019/2/11
 */

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    @Override
    public void sendMail(String email, String code) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("验证你的电子邮件地址");

            String content = "<html><head></head><body><h1>验证你的电子邮件地址</h1><h3><a href='http://localhost:8000/member/verify?code="
                    + code + "'>http://localhost:8000/user/verify?code=" + code
                    + "</href></h3><h5>若浏览器不能正常跳转，请复制链接到地址栏</h5></body></html>";

            helper.setText(content,true);

            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
