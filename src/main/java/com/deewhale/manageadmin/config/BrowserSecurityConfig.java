package com.deewhale.manageadmin.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/********************************************
 * @author     ：pepper
 * @date       ：Created in 2021/1/7 下午4:20
 * @description：将HTTP Basic认证修改为基于表单的认证方式。
 * ******************************************/
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单方式
                .and()
                .authorizeRequests() // 授权配置
                .anyRequest()  // 所有请求
                .authenticated(); // 都需要认证
    }
}
