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
    public @ResponseBody ResponseEntity permissionInfo(@Param("page") int page,
                                                 @Param("limit") int limit){
        Page<SysPermission> permissions = sysPermissionService.findAll(page,limit);
        return ResponseUtil.success(permissions.getContent(),permissions.getTotalElements());
    }

    @RequestMapping(value="/permission/search",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity permissionSearch(@Param("page") int page,
                                                   @Param("limit") int limit,
                                                   @Param("name") String name,
                                                   @Param("permission") String permission,
                                                   @Param("enable") String enable){
        Page<SysPermission> permissions = sysPermissionService.findAll(page,limit,name,permission,enable);
        return ResponseUtil.success(permissions.getContent(),permissions.getTotalElements());
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:creat")
    @RequestMapping(value="/permission/add",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity permissionsAdd(@Param("name") String name,
                                                @Param("permissions") String permission){
        SysPermission sysPermission = null;
        sysPermission = sysPermissionService.findByPermission(permission);
        if(sysPermission != null){
            return ResponseUtil.error("该权限标识已存在");
        }
        SysPermission newPermission = new SysPermission();
        newPermission.setName(name);
        newPermission.setPermission(permission);
        sysPermissionService.save(newPermission);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/permission/selectbyid",method = RequestMethod.GET)
    public @ResponseBody ResponseEntity permissionSelectById(@Param("id") long id){
        SysPermission permission = sysPermissionService.findById(id);
        return ResponseUtil.success(permission);
    }

    @RequiresRoles("admin")
    @RequiresPermissions("admin:update")
    @RequestMapping(value = "/permission/update",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity permissionUpdate(@Param("id") long id,
                                                   @Param("name") String name,
                                                   @Param("permission") String permission,
                                                   @Param("enable") boolean enable){
        SysPermission sysPermission = null;
        sysPermission = sysPermissionService.findByPermission(permission);
        if(sysPermission != null && sysPermission.getId() != id){
            return ResponseUtil.error("该权限标识已存在");
        }
        sysPermission = sysPermissionService.findById(id);
        sysPermission.setName(name);
        sysPermission.setPermission(permission);
        sysPermission.setEnable(enable);
        sysPermissionService.save(sysPermission);
        return ResponseUtil.success();
    }
}
