package com.keji.bookmanage.schedule;

import com.keji.bookmanage.config.ResourceFactory;
import com.keji.bookmanage.entity.BorrowRecord;
import com.keji.bookmanage.service.BorrowRecordService;
import com.keji.bookmanage.service.SendEmailService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @auther tangguangchuan
 * @date 2021/1/13 下午1:31
 * 向用户发邮件提示借书到期或逾期归还
 */
@Component
@Slf4j
@PropertySource(value = "classpath:application.yml", factory = ResourceFactory.class)
@ConfigurationProperties("schedule")
@Data
public class SendEmailSchedule {
    private String enable;
    private String cron;

    @Autowired
    BorrowRecordService borrowRecordService;
    @Autowired
    SendEmailService sendEmailService;

    @Scheduled(cron = "${schedule.cron}")
    public void sendEmail() {
        if (!Boolean.parseBoolean(enable)) {
            log.info("定时任务未开启");
            return;
        }
        log.info("开始执行定时任务");
        //查询逾期的借阅记录并发邮件提示归还
        LocalDateTime now = LocalDateTime.now();
        List<BorrowRecord> overRecords = borrowRecordService.findByReturnDateAndStatus(now);
        if (overRecords.size() > 0) {
            //定义set存储邮箱信息,同一用户可能有多笔逾期记录,只发送一次邮件提醒
            Set<String> set = new HashSet<>();
            String msg = "尊敬的用户,您好!您借阅的图书已逾期,请尽快归还!(借阅详情请登录图书借阅系统查看)";
            for (int i = 0; i < overRecords.size(); i++) {
                //修改状态为1逾期
                overRecords.get(i).setStatus(1);
                set.add(overRecords.get(i).getSysUser().getEmail());
            }
            borrowRecordService.saveAll(overRecords);
            //群发邮件通知
            sendEmailService.sendSimpleMail(set.toArray(new String[set.size()]),msg);
        }
        log.info("执行定时任务结束");
    }
}
