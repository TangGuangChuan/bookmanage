package com.keji.bookmanage.controller;

import com.keji.bookmanage.config.MyMatcher;
import com.keji.bookmanage.entity.SysRole;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.service.SysRoleService;
import com.keji.bookmanage.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 上午11:47
 */
@Controller
public class RegisterController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> register(@Param("username")String username,
                    @Param("password")String password,
                    @Param("email")String email){
        Map<String,Object> map = new HashMap<>();
        SysUser sysUser = null;
        sysUser = sysUserService.findByUsername(username);
        if(sysUser != null){
            map.put("error","该用户已注册");
            return map;
        }
        sysUser = sysUserService.findByEmail(email);
        if(sysUser != null){
            map.put("error","该邮箱已注册");
            return map;
        }
        SysUser newUser = new SysUser();
        newUser.setUsername(username);
        String pwd = new MyMatcher().encrypt(password,newUser.getSalt());
        newUser.setPassword(pwd);
        newUser.setEmail(email);
        //默认注册用户为普通用户(user),拥有权限(user:crud);
        SysRole role = sysRoleService.findByRole("user");
        List<SysRole> roles = new ArrayList<>();
        roles.add(role);
        newUser.setRoles(roles);
        sysUserService.save(newUser);
        //注册成功后直接登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);
        map.put("msg","注册成功");
        return map;
    }
}
