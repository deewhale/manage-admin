package com.deewhale.manageadmin.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/********************************************
 * @author     ：pepper
 * @date       ：Created in 2021/1/8 下午4:26
 * @description：验证码
 * ******************************************/
@Data
public class ImageCode {
    private BufferedImage image; //图片

    private String code; // 验证码

    private LocalDateTime expireTime; // 过期时间

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
