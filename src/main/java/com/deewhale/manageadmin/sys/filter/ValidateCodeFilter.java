package com.deewhale.manageadmin.sys.filter;

import com.deewhale.manageadmin.sys.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/********************************************
 * @author     ：pepper
 * @date       ：Created in 2021/1/8 下午5:44
 * @description：验证码校验
 *
 * ValidateCodeFilter继承了org.springframework.web.filter.OncePerRequestFilter，该过滤器只会执行一次。
 * ******************************************/
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (StringUtils.equalsIgnoreCase("/login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {  //必须是 post 的 login 请求
            try {
                validateCode(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void validateCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

    }
}
