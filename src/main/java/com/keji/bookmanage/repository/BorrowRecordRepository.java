package com.keji.bookmanage.repository;

import com.keji.bookmanage.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/27 下午4:24
 */
@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long>, QuerydslPredicateExecutor<BorrowRecord> {
}
