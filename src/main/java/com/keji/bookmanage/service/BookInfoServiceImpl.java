package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.repository.BookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午3:31
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    BookInfoRepository bookInfoRepository;
    @Override
    public List<BookInfo> findAll() {
        return bookInfoRepository.findAll();
    }
}
