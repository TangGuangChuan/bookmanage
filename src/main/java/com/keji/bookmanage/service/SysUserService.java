package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @auther tangguangchuan
 * @date 2021/1/12 下午2:34
 */
public interface SysUserService {

    SysUser findByUsernameOrEmail(String username);

    @Transactional
    SysUser save(SysUser sysUser);

    Optional<SysUser> findById(Long id);

    SysUser findByUsername(String username);

    SysUser findByEmail(String email);

    Page<SysUser> findAll(int page, int limit);

    Page<SysUser> findAll(int page, int limit, String username, String email, String locked);
}
