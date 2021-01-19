package com.keji.bookmanage.repository;

import com.keji.bookmanage.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午1:40
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole,Long> {
    SysRole findByRole(String role);
}
