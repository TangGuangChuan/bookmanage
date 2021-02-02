package com.keji.bookmanage.service;

import com.google.common.base.Strings;
import com.keji.bookmanage.entity.QSysUser;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.repository.SysUserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Page<SysUser> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by(Sort.Direction.DESC,"createAt"));
        return sysUserRepository.findAll(pageable);
    }

    @Override
    public Page<SysUser> findAll(int page, int limit, String username, String email, String locked) {
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        QSysUser qSysUser = QSysUser.sysUser;
        if(!Strings.isNullOrEmpty(username)){
            expression = expression.and(qSysUser.username.eq(username));
        }
        if(!Strings.isNullOrEmpty(email)){
            expression = expression.and(qSysUser.email.eq(email));
        }
        if(!Strings.isNullOrEmpty(locked)){
            expression = expression.and(qSysUser.locked.eq(Boolean.valueOf(locked)));
        }
        Pageable pageable = PageRequest.of(page-1,limit);
        return sysUserRepository.findAll(expression,pageable);
    }
}
