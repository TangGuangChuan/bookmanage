package com.keji.bookmanage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午2:55
 */
@Entity
@Setter
@Getter
public class BookInfo extends BaseEntity {
    @Column(unique = true)
    private String bookname;
    private String auther;
    private String introduce;
    private int number;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private BookType bookType;
    //借阅次数
    private int borrowNum;
    //图书封面
    private String bookImg;
}
