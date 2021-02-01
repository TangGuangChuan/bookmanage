package com.keji.bookmanage.service;

import com.google.common.base.Strings;
import com.keji.bookmanage.entity.BookType;
import com.keji.bookmanage.entity.QBookType;
import com.keji.bookmanage.repository.BookTypeRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
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

    @Override
    public Page<BookType> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page-1,limit);
        return bookTypeRepository.findAll(pageable);
    }

    @Override
    public Page<BookType> search(int page, int limit, String typeCode, String typeName) {
        QBookType qBookType = QBookType.bookType;
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        if(!Strings.isNullOrEmpty(typeCode)){
            expression = expression.and(qBookType.typeCode.eq(typeCode));
        }
        if(!Strings.isNullOrEmpty(typeName)){
            expression = expression.and(qBookType.typeName.eq(typeName));
        }
        Pageable pageable = PageRequest.of(page-1,limit);
        return bookTypeRepository.findAll(expression,pageable);
    }

    @Override
    public void saveAndFlush(BookType bookType) {
        bookTypeRepository.saveAndFlush(bookType);
    }

    @Override
    public void deleteById(Long id) {
        bookTypeRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        List<BookType> list = bookTypeRepository.findAllById(Arrays.asList(ids));
        bookTypeRepository.deleteInBatch(list);
    }
}
