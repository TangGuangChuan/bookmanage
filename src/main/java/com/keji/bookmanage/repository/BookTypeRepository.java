package com.keji.bookmanage.repository;

import com.keji.bookmanage.entity.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @auther tangguangchuan
 * @date 2021/1/26 上午11:04
 */
@Repository
public interface BookTypeRepository extends JpaRepository<BookType,Long>, QuerydslPredicateExecutor<BookType> {
    BookType findByTypeCode(String typeCode);
}
