package com.keji.bookmanage.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/12 上午10:04
 */
@Entity
@Getter
@Setter
public class SysRole extends BaseEntity {
    private String role;
    //角色描述
    private String description;
    //是否可用，不可用将不会添加给用户
    private Boolean enable;
    //角色－权限多对多关系
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SysRolePermission",
            joinColumns = {@JoinColumn(name="roleId")},
            inverseJoinColumns = {@JoinColumn(name="permissionId")})
    private List<SysPermission> permissions;
    //用户-角色关系定义
    @ManyToMany
    @JoinTable(
            name = "SysUserRole",
            joinColumns = {@JoinColumn(name="roleId")},
            inverseJoinColumns = {@JoinColumn(name="uid")}
    )
    private List<SysUser> sysUsers;
}
