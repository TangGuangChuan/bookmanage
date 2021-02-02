package com.keji.bookmanage.controller;

import com.keji.bookmanage.entity.SysPermission;
import com.keji.bookmanage.entity.SysRole;
import com.keji.bookmanage.service.SysPermissionService;
import com.keji.bookmanage.service.SysRoleService;
import com.keji.bookmanage.util.ResponseEntity;
import com.keji.bookmanage.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther tangguangchuan
 * @date 2021/2/2 上午11:31
 */
@Controller
public class PermissionController {
    @Autowired
    SysPermissionService sysPermissionService;

    @RequestMapping(value = "/permission/getpermissions",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getPermissions(){
        List<SysPermission> permissions = sysPermissionService.findAll();
        return ResponseUtil.success(permissions);
    }

    @RequestMapping(value="/permission/list",method = RequestMethod.GET)
    public String permissionList(){
        return "admin/permissionlist";
    }

    @RequestMapping(value="/permission/info",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity roleInfo(@Param("page") int page,
                                                 @Param("limit") int limit){
        Page<SysPermission> permissions = sysPermissionService.findAll(page,limit);
        return ResponseUtil.success(permissions.getContent(),permissions.getTotalElements());
    }

//    @RequestMapping(value="/role/search",method = RequestMethod.GET)
//    public @ResponseBody ResponseEntity roleSearch(@Param("page") int page,
//                                                   @Param("limit") int limit,
//                                                   @Param("rolename") String rolename,
//                                                   @Param("role") String role,
//                                                   @Param("enable") String enable){
//        Page<SysRole> roles = sysRoleService.findAll(page,limit,rolename,role,enable);
//        return ResponseUtil.success(roles.getContent(),roles.getTotalElements());
//    }
//
//    @RequiresRoles("admin")
//    @RequiresPermissions("admin:creat")
//    @RequestMapping(value="/role/add",method = RequestMethod.POST)
//    public @ResponseBody ResponseEntity roleAdd(@Param("rolename") String rolename,
//                                                @Param("role") String role,
//                                                @Param("permissions") String[] permissions){
//        SysRole sysRole = null;
//        sysRole = sysRoleService.findByRole(role);
//        if(sysRole != null){
//            return ResponseUtil.error("该权限标识已存在");
//        }
//        SysRole newRole = new SysRole();
//        newRole.setRoleName(rolename);
//        newRole.setRole(role);
//
//        List<SysPermission> permissionList = sysPermissionService.findByPermissions(permissions);
//        newRole.setPermissions(permissionList);
//        sysRoleService.save(newRole);
//        return ResponseUtil.success();
//    }
//
//    @RequestMapping(value = "/role/selectbyid",method = RequestMethod.GET)
//    public @ResponseBody ResponseEntity roleSelectById(@Param("id") long id){
//        SysRole sysRole = sysRoleService.findById(id);
//        return ResponseUtil.success(sysRole);
//    }
//
//    @RequiresRoles("admin")
//    @RequiresPermissions("admin:update")
//    @RequestMapping(value = "/role/update",method = RequestMethod.POST)
//    public @ResponseBody ResponseEntity roleUpdate(@Param("id") long id,
//                                                   @Param("rolename") String rolename,
//                                                   @Param("role") String role,
//                                                   @Param("enable") boolean enable,
//                                                   @Param("permissions") String[] permissions){
//        SysRole sysRole = null;
//        sysRole = sysRoleService.findByRole(role);
//        if(sysRole != null && sysRole.getId() != id){
//            return ResponseUtil.error("该权限标识已存在");
//        }
//        sysRole = sysRoleService.findById(id);
//        sysRole.setRole(role);
//        sysRole.setRoleName(rolename);
//        sysRole.setEnable(enable);
//        List<SysPermission> permissionList = sysPermissionService.findByPermissions(permissions);
//        sysRole.setPermissions(permissionList);
//        sysRoleService.save(sysRole);
//        return ResponseUtil.success();
//    }
}
