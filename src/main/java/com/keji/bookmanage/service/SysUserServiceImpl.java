package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return sysUserRepository.saveAndFlush(sysUser);
    }

    @Override
    public Optional<SysUser> findById(Long id) {
        return sysUserRepository.findById(id);
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public SysUser findByEmail(String email) {
        return sysUserRepository.findByEmail(email);
    }
}
