package com.lsy.test.security.code;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Data
@Component("imageValidateCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator<ImageCode>{


    @Override
    public ImageCode generate(ServletWebRequest request) {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(95, 25, 4, 35);
        return new ImageCode(captcha.getImage(), captcha.getCode(), 1);
    }
}
