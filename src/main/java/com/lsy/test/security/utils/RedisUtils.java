package com.lsy.test.security.utils;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

/**
 * @author lsy
 */
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private Gson json;

    private static Gson gson = new Gson();

    private static StringRedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        RedisUtils.redisTemplate = template;
        RedisUtils.gson = json;
    }

    public static <T> void set(String key, T value, Type type) {
        saveValue(key, value, type, -1);
    }

    public static <T> T get(String key, Type type) {
        return getValue(key, type);
    }

    public static void del(String key) {
        redisTemplate.delete(key);
    }

    public static <T> T getValue(String key, Type type) {
        T t = null;
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String valueStr = opsForValue.get(key);
        if (null != valueStr) {
            t = gson.fromJson(valueStr, type);
        }
        return t;
    }

    public static <T> void saveValue(String key, T value, Type type, int min) {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String valueStr = gson.toJson(value, type);
        if (min > 0) {
            opsForValue.set(key, valueStr, min, TimeUnit.MINUTES);
        }else {
            opsForValue.set(key, valueStr);
        }
    }
}
