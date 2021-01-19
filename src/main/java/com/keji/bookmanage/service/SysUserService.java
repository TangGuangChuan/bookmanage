package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.SysRole;
import com.keji.bookmanage.entity.SysUser;

/**
 * @auther tangguangchuan
 * @date 2021/1/12 下午2:34
 */
public interface SysUserService {
    SysUser findByUsernameOrEmail(String username);

    SysUser save(SysUser sysUser);
}
