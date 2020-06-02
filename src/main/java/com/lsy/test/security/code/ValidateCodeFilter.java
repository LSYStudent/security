package com.lsy.test.security.code;

import com.lsy.test.security.exception.ValidateCodeException;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    @Setter
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private ValidateCodeProcessorHolder holder;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType codeType = getValidateCodeType(request);
        try {
            holder.findValidateCodeProcessor(codeType)
                    .validate(new ServletWebRequest(request, response));
        } catch (ValidateCodeException e) {
            failureHandler.onAuthenticationFailure(request,response,e);
            return;
        }
        filterChain.doFilter(request,response);
    }

    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        String uri = request.getRequestURI();
        Set<String> urls = urlMap.keySet();
        for (String url : urls) {
            if (pathMatcher.match(request.getContextPath()+url, uri)) {
                result = urlMap.get(url);
            }
        }
        return result;
    }
}
