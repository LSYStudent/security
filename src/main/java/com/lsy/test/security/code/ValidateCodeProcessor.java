package com.lsy.test.security.code;

import com.lsy.test.security.exception.ValidateCodeException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

public interface ValidateCodeProcessor {

    /**
     * 创建验证码
     * @param request
     */
    void create(ServletWebRequest request) throws IOException;

    /**
     * 校验验证码
     * @param request
     */
    void validate(ServletWebRequest request) throws ValidateCodeException;
}
