package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.BorrowRecord;
import com.keji.bookmanage.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午3:30
 */
public interface BorrowRecordService {

    @Transactional
    void saveAndFlush(BookInfo bookInfo, SysUser user,int borrowDays);

    Page<BorrowRecord> findAllByPage(int page, int limit);

    @Transactional
    void deleteByIds(Long[] ids);

    List<BorrowRecord> findByUsername(String username);

    Page<BorrowRecord> searchBorrow(int page, int limit, String bookname, String username, String status);
}
