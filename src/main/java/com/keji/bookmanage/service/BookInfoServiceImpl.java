package com.keji.bookmanage.service;

import com.google.common.base.Strings;
import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.QBookInfo;
import com.keji.bookmanage.repository.BookInfoRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    @Override
    public void deleteById(Long id) {
        bookInfoRepository.deleteById(id);
    }

    @Override
    public BookInfo selectById(Long id) {
        return bookInfoRepository.findById(id).get();
    }

    @Override
    public BookInfo selectByBookname(String bookname) {
        return bookInfoRepository.findByBookname(bookname);
    }

    @Override
    public void updateById(BookInfo bookInfo) {
        bookInfoRepository.saveAndFlush(bookInfo);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        List<BookInfo> bookInfos = bookInfoRepository.findAllById(Arrays.asList(ids));
        bookInfoRepository.deleteInBatch(bookInfos);
    }

    @Override
    public List<BookInfo> findByType_id(Long typeId) {
        QBookInfo qBookInfo = QBookInfo.bookInfo;
        BooleanExpression expression = qBookInfo.bookType.id.eq(typeId);
        return (List<BookInfo>) bookInfoRepository.findAll(expression);
    }

    @Override
    public List<BookInfo> findByType_ids(Long[] ids) {
        QBookInfo qBookInfo = QBookInfo.bookInfo;
        BooleanExpression expression = qBookInfo.bookType.id.in(ids);
        return (List<BookInfo>) bookInfoRepository.findAll(expression);
    }

    @Override
    public List<BookInfo> findByCreateAt(LocalDateTime start, LocalDateTime end) {
        QBookInfo qBookInfo = QBookInfo.bookInfo;
        BooleanExpression expression = qBookInfo.createAt.between(start,end);
        return (List<BookInfo>) bookInfoRepository.findAll(expression);
    }
}
