package com.keji.bookmanage.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/12 上午11:17
 */
@Entity
@Getter
@Setter
public class SysPermission extends BaseEntity {
    //权限名称
    private String name;
    //类型
    @Column(columnDefinition = "enum('menu','button')")
    private String type;
    //路径
    private String url;
    //权限字符串menu例子：role:*,button例子：role:create,role:update
    private String permission;
    private Boolean enable = Boolean.FALSE;
    //角色-权限关系
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SysRolePermission",
            joinColumns = {@JoinColumn(name="permissionId")},
            inverseJoinColumns = {@JoinColumn(name="roleId")}
    )
    private List<SysRole> roles;
}
