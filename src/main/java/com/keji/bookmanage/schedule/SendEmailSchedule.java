package com.keji.bookmanage.schedule;

import com.keji.bookmanage.config.ResourceFactory;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @auther tangguangchuan
 * @date 2021/1/13 下午1:31
 */
@Component
@Slf4j
@PropertySource(value = "classpath:application.yml",factory = ResourceFactory.class)
@ConfigurationProperties("schedule")
@Data
public class SendEmailSchedule {
    private String enable;
    private String cron;
    //@Scheduled(cron = "${schedule.cron}")
    public void sendEmail(){
        System.out.println(cron+enable);
        if(!Boolean.parseBoolean(enable)){
            log.info("定时任务未开启");
        }
        log.info("开始执行定时任务");
    }
}
