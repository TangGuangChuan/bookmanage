package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.SysRole;
import com.keji.bookmanage.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午1:42
 */
public interface SysRoleService {
    SysRole findByRole(String role);

    @Transactional
    SysRole save(SysRole sysRole);

    List<SysRole> findAll();

    List<SysRole> findByRoles(String[] roles);

    Page<SysRole> findAll(int page, int limit);

    Page<SysRole> findAll(int page, int limit, String rolename, String role, String enable);

    SysRole findById(long id);
}
