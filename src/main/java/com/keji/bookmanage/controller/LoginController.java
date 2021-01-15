package com.keji.bookmanage.controller;

import com.keji.bookmanage.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @auther tangguangchuan
 * @date 2021/1/12 下午3:56
 */
@Controller
public class LoginController {

    @Autowired
    SysUserService sysUserService;

    //退出的时候是get请求，主要是用于退出
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    //post登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@Param("username")String username,
                        @Param("password")String password,
                        ModelMap modelMap){
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        try{
            subject.login(usernamePasswordToken);
        }catch (UnknownAccountException e){
            modelMap.put("error","用户不存在,请先注册");
            return "login";
        }catch (IncorrectCredentialsException e){
            modelMap.put("error","密码错误,请重新输入");
            return "login";
        } catch (Exception e){
            modelMap.put("error","未知错误,请联系管理员");
            return "login";
        }
        return "index";
    }

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    @ResponseBody
    public String error(){
        return "error ok!";
    }

    //注解的使用
    @RequiresRoles("admin")
    @RequiresPermissions("user:list")
    @RequestMapping(value = "/create")
    @ResponseBody
    public String create(){
        return "Create success!";
    }
}
