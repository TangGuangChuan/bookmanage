package com.keji.bookmanage.config;

import com.keji.bookmanage.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @auther tangguangchuan
 * @date 2021/1/11 下午4:48
 */
public class UserAuditor implements AuditorAware<String> {
    /**
     * 实现审计功能获取当前创建或修改的用户
     *
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        try{
            Object obj = SecurityUtils.getSubject().getPrincipal();
            if(obj == null){
                return null;
            }
            SysUser user = (SysUser) obj;
            return Optional.ofNullable(user.getUsername());
        }catch (UnavailableSecurityManagerException e){
            /**
             * 定时任务里调用SecurityUtils.getSubject()会报UnavailableSecurityManagerException异常
             * 调用SecurityUtils.getSubject()必须为有效的http连接,或者tcp连接,而在定时任务里,不是http连接,也不是tcp连接
             */
            return null;
        }
    }
}
