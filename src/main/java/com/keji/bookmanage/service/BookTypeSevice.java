package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.BookType;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/26 上午11:05
 */
public interface BookTypeSevice {
    BookType findByTypeCode(String typeCode);

    List<BookType> findAll();

    Page<BookType> findAll(int page, int limit);

    Page<BookType> search(int page, int limit, String typeCode, String typeName);

    void saveAndFlush(BookType bookType);

    void deleteById(Long id);

    void deleteByIds(Long[] ids);
}
