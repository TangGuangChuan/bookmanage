package com.keji.bookmanage.controller;

import com.keji.bookmanage.config.MyMatcher;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午4:15
 */
@Controller
public class SysUserController {
    @Autowired
    SysUserService sysUserService;
    @RequestMapping("/user/welcome")
    public String welcome(){
        return "admin/welcome";
    }

    @RequestMapping("/user/info")
    public String userInfo(ModelMap modelMap){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        modelMap.put("sysUser",sysUser);
        return "admin/admin-info";
    }

    @RequestMapping(value="/user/updateinfo",method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updateInfo(@Param("id")Long id,
                                   @Param("username") String username,
                                   @Param("email") String email){
        Map<String,Object> map = new HashMap<>();
        SysUser sysUser = null;
        sysUser = sysUserService.findByUsername(username);
        if(sysUser != null && sysUser.getId() != id){
            map.put("error","该用户名已存在");
            return map;
        }
        sysUser = sysUserService.findByEmail(email);
        if(sysUser != null && sysUser.getId() != id){
            map.put("error","该邮箱已存在");
            return map;
        }
        sysUser = sysUserService.findById(id).get();
        sysUser.setUsername(username);
        sysUser.setEmail(email);
        sysUserService.save(sysUser);
        map.put("msg","修改资料成功");
        return map;
    }

    @RequestMapping(value="/user/updatepwd",method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> updatePwd(@Param("id")Long id,
                                   @Param("password1") String password1,
                                  @Param("password2") String password2){
        Map<String,Object> map = new HashMap<>();
        SysUser sysUser = sysUserService.findById(id).get();
        String pwd = new MyMatcher().encrypt(password1,sysUser.getSalt());
        if(!pwd.equals(sysUser.getPassword())){
            map.put("error","密码错误!");
            return map;
        }
        sysUser.setPassword(new MyMatcher().encrypt(password2,sysUser.getSalt()));
        sysUserService.save(sysUser);
        map.put("msg","修改密码成功");
        return map;
    }
}
