package com.keji.bookmanage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

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
    private String number;
}
