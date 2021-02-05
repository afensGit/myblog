package com.bin.myblog.config;

import com.bin.myblog.pojo.User;
import com.bin.myblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权AuthorizationInfo");
        return new SimpleAuthorizationInfo();
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证AuthenticationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.checkLoginUser(token.getUsername());
        if (user == null){
            return null;
        }
        //获取session对象
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("user",user);
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");

    }
}
