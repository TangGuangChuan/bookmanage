package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.BorrowRecord;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @auther tangguangchuan
 * @date 2021/1/27 下午4:23
 */
@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    @Override
    public void saveAndFlush(BorrowRecord borrowRecord) {

    }

    @Override
    public Page<BorrowRecord> findAllByPage(int page, int limit) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public BorrowRecord selectById(Long id) {
        return null;
    }

    @Override
    public void updateById(BorrowRecord borrowRecord) {

    }

    @Override
    public void deleteByIds(Long[] ids) {

    }
}
