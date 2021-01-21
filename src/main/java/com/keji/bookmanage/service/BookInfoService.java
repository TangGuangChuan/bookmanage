package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.BookInfo;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午3:30
 */
public interface BookInfoService {
    List<BookInfo> findAll();
}
