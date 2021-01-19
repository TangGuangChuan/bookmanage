package com.keji.bookmanage.entity;

import lombok.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/1/11 下午1:34
 */
@Entity
@Getter
@Setter
public class SysUser extends BaseEntity  {
    @Column(unique = true)
    private String username;
    private String password;
    //加密密码的盐
    private String salt = new SecureRandomNumberGenerator().nextBytes().toHex();;
    //是否锁定
    private Boolean locked = Boolean.FALSE;
    //邮箱
    @Column(unique = true)
    private String email;
    //一个用户具有多个角色
    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库加载数据
    @JoinTable(name="SysUserRole",joinColumns = {@JoinColumn(name = "uid")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;
}
