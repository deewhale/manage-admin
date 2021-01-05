package com.deewhale.manageadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/********************************************
 * @author     ：pepper
 * @date       ：Created in 2021/1/4 下午4:30
 * @description：创建授权服务
 * ******************************************/
@Configurable
@EnableAuthorizationServer
public class AuthorizationServiceConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager; //表示支持password认证模式

    @Autowired
    private RedisConnectionFactory redisConnectionFactory; //登陆成功后的token需要存在redis里面

    @Autowired
    private UserDetailsService userDetailsService; //里面存放着用户信息

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("client_id") // 使用in-memory存储客户端信息
                .authorizedGrantTypes("password","refresh_token")  // 该client允许的授权类型
                .accessTokenValiditySeconds(1800) //过期时间
                .refreshTokenValiditySeconds(60 * 60 * 2)
                .resourceIds("rid")
                .scopes("all"); //允许的授权范围
//                .secret("234567890");
    }
}
