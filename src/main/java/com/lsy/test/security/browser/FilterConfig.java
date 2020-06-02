package com.lsy.test.security.browser;

import com.lsy.test.security.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Autowired
    private ValidateCodeFilter codeFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(codeFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("ValidateCodeFilter");
        return registrationBean;
    }

    @Bean
    public Filter codeFilter() {
        return new ValidateCodeFilter();
    }
}
