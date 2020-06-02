package com.lsy.test.security;

import com.lsy.test.security.config.R;
import com.lsy.test.security.exception.StudyBaseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 启动类
 * @author lsy
 */
@RestControllerAdvice
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = StudyBaseException.class)
    public R<String> exception(StudyBaseException ex) {
        return R.failure(ex.getMessage());
    }
}
