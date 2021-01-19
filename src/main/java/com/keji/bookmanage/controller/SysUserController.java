package com.keji.bookmanage.controller;

import com.keji.bookmanage.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午4:15
 */
@Controller("/user")
public class SysUserController {

    @RequestMapping("/info")
    public String userInfo(ModelMap modelMap){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject();
        modelMap.put("sysUser",sysUser);
        return "admin/admin-info";
    }
}
