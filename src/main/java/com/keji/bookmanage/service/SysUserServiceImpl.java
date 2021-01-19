package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.SysRole;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther tangguangchuan
 * @date 2021/1/12 下午2:48
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserRepository sysUserRepository;
    @Override
    public SysUser findByUsernameOrEmail(String username) {
        return sysUserRepository.findByUsernameOrEmail(username);
    }

    @Override
    public SysUser save(SysUser sysUser) {
        return sysUserRepository.save(sysUser);
    }
}
