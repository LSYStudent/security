package com.lsy.test.security.code;

import com.google.gson.reflect.TypeToken;
import com.lsy.test.security.exception.StudyBaseException;
import com.lsy.test.security.exception.ValidateCodeException;
import com.lsy.test.security.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 验证码处理器
 * @param <T>
 */
public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {


    @Autowired
    private Map<String, ValidateCodeGenerator> codeGenerator;

    public abstract void send(ServletWebRequest request, T validateCode) throws IOException;

    @Override
    public void create(ServletWebRequest request) throws IOException {
        T validate = generator(request);
        send(request, validate);
        save(request, validate);
    }

    public T generator(ServletWebRequest request) {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String name = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator<T> generator = codeGenerator.get(name);
        if (null == generator) {
            throw new StudyBaseException("验证码生成器" + name + "不存在");
        }
        return generator.generate(request);
    }

    public void save(ServletWebRequest request, ValidateCode code) {
        Type type = new TypeToken<ValidateCode>() {}.getType();
        RedisUtils.set("code", code, type);
    }

    public ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String name = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(name.toUpperCase());
    }

    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType codeType = getValidateCodeType(request);
        Type type = new TypeToken<ValidateCode>() {}.getType();
        ValidateCode code = RedisUtils.get("code", type);
        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType.getTextString() + "验证码的值不能为空");
        }

        if (code == null) {
            throw new ValidateCodeException(codeType.getTextString() + "验证码不存在");
        }

        if (code.isExpired()) {
            throw new ValidateCodeException(codeType.getTextString() + "验证码已过期");
        }

        if (!StringUtils.equals(code.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType.getTextString() + "验证码不匹配");
        }
    }
}
