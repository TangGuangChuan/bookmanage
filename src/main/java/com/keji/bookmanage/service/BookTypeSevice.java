package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.BookType;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/26 上午11:05
 */
public interface BookTypeSevice {
    BookType findByTypeCode(String typeCode);
    List<BookType> findAll();
}
