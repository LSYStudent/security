package com.lsy.test.security.browser;

import com.lsy.test.security.model.User;
import com.lsy.test.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author lsy
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    private final UserService targetService;

    private final PasswordEncoder encoder;

    @Autowired
    public MyUserDetailsService(UserService targetService, PasswordEncoder encoder) {
        this.targetService = targetService;
        this.encoder = encoder;
    }


    /**
     * 用户登录
     * @param s  用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s){
        log.info("用户名：" + s);
        User user = targetService.findUser(s);
        if (null == user) {
            throw new UsernameNotFoundException("用户未注册");
        }
        String password = encoder.encode(user.getPassword());
        return new MyUserDetails(user.setPassword(password));
    }

}
