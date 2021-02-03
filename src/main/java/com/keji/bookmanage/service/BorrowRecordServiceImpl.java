package com.keji.bookmanage.service;

import com.google.common.base.Strings;
import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.BorrowRecord;
import com.keji.bookmanage.entity.QBorrowRecord;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.repository.BorrowRecordRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/27 下午4:23
 */
@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    @Autowired
    BookInfoService bookInfoService;
    @Autowired
    BorrowRecordRepository borrowRecordRepository;

    @Override
    public void saveAndFlush(BookInfo bookInfo, SysUser user,int borrowDays) {
        BorrowRecord borrowRecord = new BorrowRecord();
        //借阅一次库存量减一,借阅记录加一
        bookInfo.setNumber(bookInfo.getNumber()-1);
        bookInfo.setBorrowNum(bookInfo.getBorrowNum()+1);
        //设置归还时间为当前时间加上借阅天数
        LocalDateTime now = LocalDateTime.now();
        now = now.plusDays(borrowDays);
        //设置借阅用户为当前登录用户
        borrowRecord.setSysUser(user);
        borrowRecord.setBookInfo(bookInfo);
        borrowRecord.setBorrowDays(borrowDays);
        borrowRecord.setReturnDate(now);
        bookInfoService.saveAndFlush(bookInfo);
        borrowRecordRepository.saveAndFlush(borrowRecord);
    }

    @Override
    public Page<BorrowRecord> findAllByPage(int page, int limit) {
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by(Sort.Direction.ASC,"status"));
        return borrowRecordRepository.findAll(pageable);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        List<BorrowRecord> records = borrowRecordRepository.findAllById(Arrays.asList(ids));
        borrowRecordRepository.deleteInBatch(records);
    }

    @Override
    public List<BorrowRecord> findByUsername(String username) {
        QBorrowRecord record = QBorrowRecord.borrowRecord;
        BooleanExpression expression = record.sysUser.username.eq(username);
        //查询状态未还或逾期的记录
        expression = expression.and(record.status.in(0,1));
        return (List<BorrowRecord>) borrowRecordRepository.findAll(expression);
    }

    @Override
    public Page<BorrowRecord> searchBorrow(int page, int limit, String bookname, String username, String status) {
        QBorrowRecord record = QBorrowRecord.borrowRecord;
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        if(!Strings.isNullOrEmpty(bookname)){
            expression = expression.and(record.bookInfo.bookname.eq(bookname));
        }
        if(!Strings.isNullOrEmpty(username)){
            expression = expression.and(record.sysUser.username.eq(username));
        }
        if(!Strings.isNullOrEmpty(status)){
            expression = expression.and(record.status.eq(Integer.parseInt(status)));
        }
        Pageable pageable = PageRequest.of(page-1,limit,Sort.by(Sort.Direction.ASC,"createAt"));
        return borrowRecordRepository.findAll(expression,pageable);
    }

    @Override
    public Page<BorrowRecord> findAllByUser(int page, int limit, String username) {
        QBorrowRecord record = QBorrowRecord.borrowRecord;
        BooleanExpression expression = record.sysUser.username.eq(username);
        Pageable pageable = PageRequest.of(page-1,limit,Sort.by(Sort.Direction.ASC,"status"));
        return borrowRecordRepository.findAll(expression,pageable);
    }

    @Override
    public BorrowRecord findById(Long id) {
        return borrowRecordRepository.findById(id).get();
    }

    @Override
    public void saveAndFlush(BorrowRecord record) {
        borrowRecordRepository.saveAndFlush(record);
    }

    @Override
    public List<BorrowRecord> findByBookId(Long id) {
        QBorrowRecord qBorrowRecord = QBorrowRecord.borrowRecord;
        BooleanExpression expression = qBorrowRecord.bookInfo.id.eq(id);
        return (List<BorrowRecord>) borrowRecordRepository.findAll(expression);
    }

    @Override
    public List<BorrowRecord> findByBookIds(Long[] ids) {
        QBorrowRecord qBorrowRecord = QBorrowRecord.borrowRecord;
        BooleanExpression expression = qBorrowRecord.bookInfo.id.in(ids);
        return (List<BorrowRecord>) borrowRecordRepository.findAll(expression);
    }

    @Override
    public List<BorrowRecord> findByReturnDateAndStatus(LocalDateTime endTime) {
        QBorrowRecord qBorrowRecord = QBorrowRecord.borrowRecord;
        BooleanExpression expression = qBorrowRecord.status.eq(0);
        expression = expression.and(qBorrowRecord.returnDate.before(endTime));
        return (List<BorrowRecord>) borrowRecordRepository.findAll(expression);
    }

    @Override
    public void saveAll(List<BorrowRecord> overRecords) {
        borrowRecordRepository.saveAll(overRecords);
    }


}
