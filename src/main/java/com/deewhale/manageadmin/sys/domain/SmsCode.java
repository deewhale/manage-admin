package com.deewhale.manageadmin.sys.domain;

import lombok.*;

import java.time.LocalDateTime;

//短信验证码
@Getter
@Setter
@ToString
public class SmsCode {

    private String code;
    private LocalDateTime expireTime;

    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public SmsCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    boolean isExpire() { //判断是否过期
        return LocalDateTime.now().isAfter(expireTime);
    }
}
