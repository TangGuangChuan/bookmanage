package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.BookInfo;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午3:30
 */
public interface BookInfoService {
    List<BookInfo> findAll();

    @Transactional
    void saveAndFlush(BookInfo bookInfo);

    Page<BookInfo> findAllByPage(int page,int limit);

    Page<BookInfo> serchBook(int page, int limit, String bookname, String auther, String type);
}
