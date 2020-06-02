package com.lsy.test.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsy.test.security.mapper.UserMapper;
import com.lsy.test.security.model.User;
import com.lsy.test.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Override
    public User findUser(String name) {
        return baseMapper.selectOne(Wrappers.<User>query().lambda().eq(User::getUserName, name));
    }

    public void test() {
        try (InputStream stream = new FileInputStream("");){
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
