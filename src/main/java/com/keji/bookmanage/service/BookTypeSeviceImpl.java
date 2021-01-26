package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.BookType;
import com.keji.bookmanage.repository.BookTypeRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/26 上午11:05
 */
@Service
public class BookTypeSeviceImpl implements BookTypeSevice {
    @Autowired
    BookTypeRepository bookTypeRepository;
    @Override
    public BookType findByTypeCode(String typeCode) {
        return bookTypeRepository.findByTypeCode(typeCode);
    }

    @Override
    public List<BookType> findAll() {
        return bookTypeRepository.findAll();
    }
}
