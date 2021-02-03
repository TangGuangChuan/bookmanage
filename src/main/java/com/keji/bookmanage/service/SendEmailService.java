package com.keji.bookmanage.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @auther tangguangchuan
 * @date 2021/2/3 下午1:36
 */
@Service
@Slf4j
public class SendEmailService {
    private static final String SUBJECT = "还书提醒";

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;
    public void sendSimpleMail(String[] to,String msg){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(SUBJECT);
        message.setTo(to);
        message.setText(msg);
        try{
            mailSender.send(message);
        }catch (Exception e){
            log.info("发送邮件失败");
        }
        log.info("发送邮件成功");
    }
}
