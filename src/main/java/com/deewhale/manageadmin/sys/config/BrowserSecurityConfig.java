package com.deewhale.manageadmin.sys.config;

import com.deewhale.manageadmin.sys.filter.ValidateCodeFilter;
import com.deewhale.manageadmin.sys.handle.SysAuthenticationFailureHandler;
import com.deewhale.manageadmin.sys.handle.SysAuthenticationSucessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/********************************************
 * @author     ：pepper
 * @date       ：Created in 2021/1/7 下午4:20
 * @description：将HTTP Basic认证修改为基于表单的认证方式。
 * ******************************************/

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysAuthenticationSucessHandler sucessHandler;

    @Autowired
    private SysAuthenticationFailureHandler failureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() // 表单登录
                // http.httpBasic() // HTTP Basic
                .loginPage("/authentication/require") // 跳转 URL
                .loginProcessingUrl("/login") // 处理表单登录 URL
                .successHandler(sucessHandler)  // 处理登录成功
                .failureHandler(failureHandler) //处理登陆失败
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository()) //token 仓库
                .tokenValiditySeconds(3600)  //有效期
                .userDetailsService(userService)  //自定义登录逻辑
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/authentication/require","/code/image","/login.html").permitAll() // 登录跳转 URL 无需认证
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and().csrf().disable();//CSRF攻击防御关闭
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //这里写入的是数据库，如果写入内存可以考虑 InMemoryTokenRepositoryImpl 的实例
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }
}
