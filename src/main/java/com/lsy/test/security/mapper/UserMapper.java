package com.lsy.test.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsy.test.security.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * mapper接口
 * @author lsy
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
