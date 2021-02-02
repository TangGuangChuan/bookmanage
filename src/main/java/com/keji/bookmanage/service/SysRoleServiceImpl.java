package com.keji.bookmanage.service;

import com.google.common.base.Strings;
import com.keji.bookmanage.entity.QSysRole;
import com.keji.bookmanage.entity.SysRole;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.repository.SysRoleRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午1:43
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    SysRoleRepository roleRepository;
    @Override
    public SysRole findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public SysRole save(SysRole sysRole) {
        return roleRepository.save(sysRole);
    }

    @Override
    public List<SysRole> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<SysRole> findByRoles(String[] roles) {
        QSysRole qSysRole = QSysRole.sysRole;
        BooleanExpression expression = qSysRole.role.in(roles);
        return (List<SysRole>) roleRepository.findAll(expression);
    }

    @Override
    public Page<SysRole> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by(Sort.Direction.DESC,"createAt"));
        return roleRepository.findAll(pageable);
    }

    @Override
    public Page<SysRole> findAll(int page, int limit, String rolename, String role, String enable) {
        QSysRole qsysRole = QSysRole.sysRole;
        BooleanExpression expression = Expressions.asBoolean(true).isTrue();
        if(!Strings.isNullOrEmpty(rolename)){
            expression = expression.and(qsysRole.roleName.eq(rolename));
        }
        if(!Strings.isNullOrEmpty(role)){
            expression = expression.and(qsysRole.role.eq(role));
        }
        if(!Strings.isNullOrEmpty(enable)){
            expression = expression.and(qsysRole.enable.eq(Boolean.valueOf(enable)));
        }
        Pageable pageable = PageRequest.of(page-1,limit);
        return roleRepository.findAll(expression,pageable);
    }

    @Override
    public SysRole findById(long id) {
        return roleRepository.findById(id).get();
    }
}
