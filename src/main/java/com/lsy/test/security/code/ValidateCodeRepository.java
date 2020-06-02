package com.lsy.test.security.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeRepository {

    /**
     * 保存验证码
     * @param request
     * @param code
     * @param codeType
     */
    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType);

    /**
     * 获取验证码
     * @param request
     * @param codeType
     * @return
     */
    String get(ServletWebRequest request, ValidateCodeType codeType);
}
