package com.lsy.test.security.code;

import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

public class SmsCodeProcessor extends AbstractValidateCodeProcessor{

    @Override
    public void send(ServletWebRequest request, ValidateCode validateCode) throws IOException {

    }
}
