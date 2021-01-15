package com.keji.bookmanage.config;

import com.keji.bookmanage.entity.SysUser;
import com.keji.bookmanage.service.SysUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @auther tangguangchuan
 * @date 2021/1/14 上午11:38
 */
public class MyMatcher extends SimpleCredentialsMatcher {

    @Autowired
    SysUserService sysUserService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        SysUser sysUser = sysUserService.findByUsername(usernamePasswordToken.getUsername());
        String pwd = encrypt(String.valueOf(usernamePasswordToken.getPassword()),sysUser.getSalt());
        String mysqlpwd = (String) info.getCredentials();
        return this.equals(pwd,mysqlpwd);
    }

    /**
     * 用MD5对密码进行加盐加密3次,避免相同密码的用户密文一样
     * @param data
     * @param salt
     * @return
     */
    private String encrypt(String data,String salt){
        String result = new Md5Hash(data,salt,3).toString();
        return  result;
    }

    public static void main(String[] args) {
        System.out.println(new Md5Hash("123456", "8d78869f470951332959580424d4bf4f", 3).toString());
    }
}
