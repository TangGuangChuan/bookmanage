package com.keji.bookmanage.service;

import com.keji.bookmanage.entity.SysRole;
import com.keji.bookmanage.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
