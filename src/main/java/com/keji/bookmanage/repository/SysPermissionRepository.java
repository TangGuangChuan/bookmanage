package com.keji.bookmanage.repository;

import com.keji.bookmanage.entity.SysPermission;
import com.keji.bookmanage.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午1:40
 */
@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission,Long> , QuerydslPredicateExecutor<SysPermission> {

    SysPermission findByPermission(String permission);
}
