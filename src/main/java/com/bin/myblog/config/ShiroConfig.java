package com.bin.myblog.config;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(securityManager);
        //设置访问权限
        Map<String,String> filterMap = new LinkedHashMap<>();


        filterMap.put("/user/doLogin","anon");
        filterMap.put("/user/**","authc");
        filterMap.put("/blog/**","authc");
        filterMap.put("/type/**","authc");
        //认证不通过跳转
        factoryBean.setLoginUrl("/login");
        factoryBean.setUnauthorizedUrl("/login");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        return factoryBean;
    }


    //创建安全管理器（defaultWebSecurityManager）
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("myRealm") CustomRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    //创建自定义Realm
    @Bean(name = "myRealm")
    public CustomRealm getRealm(){
        return new CustomRealm();
    }
}
