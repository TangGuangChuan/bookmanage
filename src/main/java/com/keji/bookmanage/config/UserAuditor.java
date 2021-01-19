package com.keji.bookmanage.config;

import com.keji.bookmanage.entity.SysUser;
import org.apache.shiro.SecurityUtils;
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
        Object obj = SecurityUtils.getSubject().getPrincipal();
        return Optional.ofNullable(obj == null ? null : obj.toString());
    }

}
