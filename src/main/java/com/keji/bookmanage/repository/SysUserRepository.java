package com.keji.bookmanage.repository;

import com.keji.bookmanage.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @auther tangguangchuan
 * @date 2021/1/12 下午2:47
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    @Query("select u from SysUser u where u.username = :username or u.email = :username")
    SysUser findByUsernameOrEmail(@Param("username") String username);

    SysUser findByUsername(String username);
    SysUser findByEmail(String email);
}
