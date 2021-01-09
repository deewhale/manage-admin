package com.deewhale.manageadmin.sys.exception;

import org.springframework.security.core.AuthenticationException;

/********************************************
 * @author     ：pepper
 * @date       ：Created in 2021/1/8 下午5:40
 * @description：验证码错误
 * ******************************************/
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7776082974707867141L;

    ValidateCodeException(String message) {
        super(message);
    }
}
