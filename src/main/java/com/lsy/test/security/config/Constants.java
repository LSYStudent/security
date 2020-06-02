package com.lsy.test.security.config;

/**
 * 业务常量类
 * @author lsy
 */
public interface Constants {

    /**
     * 成功标识
     */
    Integer SUCCESS = 200;

    /**
     * 失败标识
     */
    Integer FAILURE = 600;

    /**
     * 正常
     */
    Integer NORMAL = 1;

    /**
     * 异常，非法
     */
    Integer ILLEGAL = 0;

    /**
     * 是否过期
     */
    Integer EXPIRED = 101;

    /**
     * 是否冻结
     */
    Integer LOCKED = 102;
}
