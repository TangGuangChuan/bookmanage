package com.keji.bookmanage.config;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @auther tangguangchuan
 * @date 2021/1/21 上午9:15
 */
public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 重写登录成功跳转页面方法,解决首次登录成功时跳转页面默认/报404问题
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request,response,getSuccessUrl(),null,true);
    }
}
