package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.SysPermission;
import com.keji.bookmanage.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午1:42
 */
public interface SysPermissionService {

    List<SysPermission> findByPermissions(String[] permissions);

    List<SysPermission> findAll();

    Page<SysPermission> findAll(int page, int limit);

    Page<SysPermission> findAll(int page, int limit, String name, String permission, String enable);

    SysPermission findByPermission(String permission);

    void save(SysPermission newPermission);

    SysPermission findById(long id);
}
