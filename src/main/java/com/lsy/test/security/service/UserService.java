package com.lsy.test.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsy.test.security.model.User;

/**
 * 用户服务类
 * @author lsy
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户
     * @param name
     * @return
     */
    User findUser(String name);

}
