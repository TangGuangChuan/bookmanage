package com.keji.bookmanage.repository;

import com.keji.bookmanage.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther tangguangchuan
 * @date 2021/1/12 下午2:47
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    SysUser findByUsername(String username);

}
