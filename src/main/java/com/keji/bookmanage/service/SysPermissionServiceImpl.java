package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.QSysPermission;
import com.keji.bookmanage.entity.SysPermission;
import com.keji.bookmanage.repository.SysPermissionRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/2/2 下午4:46
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    SysPermissionRepository sysPermissionRepository;

    @Override
    public List<SysPermission> findByPermissions(String[] permissions) {
        QSysPermission qSysPermission = QSysPermission.sysPermission;
        BooleanExpression expression = qSysPermission.permission.in(permissions);
        return (List<SysPermission>) sysPermissionRepository.findAll(expression);
    }

    @Override
    public List<SysPermission> findAll() {
        return sysPermissionRepository.findAll();
    }

    @Override
    public Page<SysPermission> findAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page-1,limit);
        return sysPermissionRepository.findAll(pageable);
    }
}
