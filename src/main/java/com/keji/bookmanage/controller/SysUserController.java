package com.keji.bookmanage.controller;

import com.keji.bookmanage.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午4:15
 */
@Controller
public class SysUserController {

    @RequestMapping("/user/info")
    public String userInfo(ModelMap modelMap){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        modelMap.put("sysUser",sysUser);
        return "admin/admin-info";
    }

    @RequestMapping(value="/update/info",method = RequestMethod.POST)
    public String updateInfo(){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject();
        return "admin/admin-info";
    }
}
