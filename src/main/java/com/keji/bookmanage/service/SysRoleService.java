package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.SysRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午1:42
 */
public interface SysRoleService {
    SysRole findByRole(String role);

    @Transactional
    SysRole save(SysRole sysRole);
}
