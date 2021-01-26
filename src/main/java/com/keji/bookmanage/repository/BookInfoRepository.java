package com.keji.bookmanage.repository;

import com.keji.bookmanage.entity.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 下午3:29
 */
@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Long> , QuerydslPredicateExecutor<BookInfo> {
}
