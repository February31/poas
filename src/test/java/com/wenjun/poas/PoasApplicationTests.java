package com.wenjun.poas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

@SpringBootTest
class PoasApplicationTests {
    @Resource
    JavaMailSender sender;

    @Test
    void contextLoads() {
    }

    @Test
    void sendEmail(){
        String address = "358190915@qq.com";
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1453470090@qq.com");
        mailMessage.setTo(address);
        mailMessage.setSubject("title");
        mailMessage.setText("content");
        sender.send(mailMessage);
    }

}
