package com.keji.bookmanage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @auther tangguangchuan
 * @date 2021/1/27 下午3:40
 */
@Entity
@Getter
@Setter
public class BorrowRecord extends BaseEntity {
    //状态:1未还,2已还,3逾期
    private int status;
    //用户信息
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private SysUser sysUser;
    //图书信息
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private BookInfo bookInfo;
    //借阅天数
    private int borrowDays;
    //归还时间
    private Date returnDate;
}
