package com.lsy.test.security.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator<T extends ValidateCode> {

    /**
     * 生成验证码
     * @param request
     * @return
     */
    T generate(ServletWebRequest request);
}
