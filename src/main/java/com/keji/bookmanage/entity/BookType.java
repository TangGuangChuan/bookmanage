package com.keji.bookmanage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @auther tangguangchuan
 * @date 2021/1/26 上午9:21
 */
@Entity
@Getter
@Setter
public class BookType extends BaseEntity {
    @Column(unique = true)
    private String typeCode;

    private String typeName;

}
