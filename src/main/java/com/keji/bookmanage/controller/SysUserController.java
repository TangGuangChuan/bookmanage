package com.keji.bookmanage.controller;

import com.keji.bookmanage.config.MyMatcher;
import com.keji.bookmanage.entity.SysRole;
import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.service.SysRoleService;
import com.keji.bookmanage.service.SysUserService;
import com.keji.bookmanage.util.ResponseEntity;
import com.keji.bookmanage.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther tangguangchuan
 * @date 2021/1/18 下午4:15
 */
@Controller
public class SysUserController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping("/user/welcome")
    public String welcome(){
        return "admin/welcome";
    }

    @RequestMapping("/user/myinfo")
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

    @RequestMapping(value="/user/list",method = RequestMethod.GET)
    public String userList(){
        return "admin/userlist";
    }

    @RequestMapping(value="/user/info",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity userInfo(@Param("page") int page,
                            @Param("limit") int limit){
        Page<SysUser> users = sysUserService.findAll(page,limit);
        return ResponseUtil.success(users.getContent(),users.getTotalElements());
    }

    @RequestMapping(value="/user/search",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity userSearch(@Param("page") int page,
                                                   @Param("limit") int limit,
                                                   @Param("username") String username,
                                                   @Param("email") String email,
                                                   @Param("locked") String locked){
        Page<SysUser> users = sysUserService.findAll(page,limit,username,email,locked);
        return ResponseUtil.success(users.getContent(),users.getTotalElements());
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:creat")
    @RequestMapping(value="/user/add",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity userAdd(@Param("username") String username,
                                                   @Param("email") String email,
                                                   @Param("roles") String[] roles){
        SysUser sysUser = null;
        sysUser = sysUserService.findByUsername(username);
        if(sysUser != null){
            return ResponseUtil.error("该用户已存在");
        }
        sysUser = sysUserService.findByEmail(email);
        if(sysUser != null){
            return ResponseUtil.error("该邮箱已存在");
        }
        SysUser newUser = new SysUser();
        newUser.setUsername(username);
        newUser.setEmail(email);
        //初始密码123456
        String pwd = new MyMatcher().encrypt("123456",newUser.getSalt());
        newUser.setPassword(pwd);
        List<SysRole> roleList = sysRoleService.findByRoles(roles);
        newUser.setRoles(roleList);
        sysUserService.save(newUser);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/user/selectbyid",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity userSelectById(@Param("id") long id){
        SysUser sysUser = sysUserService.findById(id).get();
        return ResponseUtil.success(sysUser);
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:update")
    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity userUpdate(@Param("id") long id,
                                                   @Param("username") String username,
                                                   @Param("email") String email,
                                                   @Param("locked") boolean locked,
                                                   @Param("roles") String[] roles){
        SysUser sysUser = null;
        sysUser = sysUserService.findByUsername(username);
        if(sysUser != null && sysUser.getId() != id){
            return ResponseUtil.error("该用户名已存在");
        }
        sysUser = sysUserService.findByEmail(email);
        if(sysUser != null && sysUser.getId() != id){
            return ResponseUtil.error("该邮箱已存在");
        }
        sysUser = sysUserService.findById(id).get();
        sysUser.setUsername(username);
        sysUser.setEmail(email);
        sysUser.setLocked(locked);
        List<SysRole> roleList = sysRoleService.findByRoles(roles);
        sysUser.setRoles(roleList);
        sysUserService.save(sysUser);
        return ResponseUtil.success();
    }

}
