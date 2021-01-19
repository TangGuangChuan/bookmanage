package com.keji.bookmanage.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther tangguangchuan
 * @date 2021/1/12 下午12:00
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器
        /*
        anon：无需认证就可以访问
        authc：必须认证了才能通过
        user：必须拥有记住我功能才能用
        perms：拥有对某个资源的权限才可以访问
        role：拥有某个角色权限才能访问
        */
        Map<String,String>  filterChainMap = new LinkedHashMap<String,String>();
        //配置不会被拦截的链接,从上至下顺序执行
        //配置静态目录允许访问,springboot默认是static目录,因此直接配置static下面的路径
        filterChainMap.put("/admin/**","anon");
        //配置退出
        filterChainMap.put("/logout","logout");
        //放行注册请求
        filterChainMap.put("/register","anon");
        //所有都需要认证,/**配置会导致static资源访问不了,具体认证需配置具体路径
        filterChainMap.put("/**","authc");
        //配置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //配置认证成功跳转页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //配置未授权页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(credentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher(){
        return new MyMatcher();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager());
        return sourceAdvisor;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
