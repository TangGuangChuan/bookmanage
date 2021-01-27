package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.BookInfo;
import com.keji.bookmanage.entity.BorrowRecord;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午3:30
 */
public interface BorrowRecordService {

    @Transactional
    void saveAndFlush(BorrowRecord borrowRecord);

    Page<BorrowRecord> findAllByPage(int page, int limit);

    @Transactional
    void deleteById(Long id);

    BorrowRecord selectById(Long id);

    @Transactional
    void updateById(BorrowRecord borrowRecord);

    @Transactional
    void deleteByIds(Long[] ids);
}
