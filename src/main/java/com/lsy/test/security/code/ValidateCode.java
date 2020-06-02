package com.lsy.test.security.code;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 1L;

    public ValidateCode() {

    }

    //验证码
    private String code;

    //过期时间
    private LocalDateTime expiredTime;

    //过期分钟数
    private int expiredMinute;

    public ValidateCode(String code, int expiredMinute) {
        this.code = code;
        this.expiredMinute = expiredMinute;
        this.expiredTime = LocalDateTime.now().plusMinutes(expiredMinute);
    }

    public Boolean isExpired() {
        return LocalDateTime.now().isAfter(expiredTime);
    }
}
