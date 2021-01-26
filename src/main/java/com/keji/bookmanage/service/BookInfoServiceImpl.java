package com.keji.bookmanage.service;

import com.google.common.base.Strings;
import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.QBookInfo;
import com.keji.bookmanage.repository.BookInfoRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.commons.collections.functors.ExceptionPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.beans.Expression;
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

    @Override
    public void saveAndFlush(BookInfo bookInfo) {
        bookInfoRepository.saveAndFlush(bookInfo);
    }

    @Override
    public Page<BookInfo> findAllByPage(int page, int limit) {
        Pageable pageable = PageRequest.of(page-1,limit,Sort.by(Sort.Direction.DESC,"createAt"));
        return bookInfoRepository.findAll(pageable);
    }

    @Override
    public Page<BookInfo> serchBook(int page, int limit, String bookname, String auther, String type) {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        QBookInfo qBookInfo = QBookInfo.bookInfo;
        if(!Strings.isNullOrEmpty(bookname)){
            expression = expression.and(qBookInfo.bookname.contains(bookname));
        }
        if(!Strings.isNullOrEmpty(auther)){
            expression = expression.and(qBookInfo.auther.contains(auther));
        }
        if(!Strings.isNullOrEmpty(type)){
            expression = expression.and(qBookInfo.bookType.typeCode.eq(type));
        }
        Pageable pageable = PageRequest.of(page-1,limit,Sort.by(Sort.Direction.DESC,"createAt"));

        return bookInfoRepository.findAll(expression,pageable);
    }
}
