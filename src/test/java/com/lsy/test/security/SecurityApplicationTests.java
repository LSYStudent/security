package com.lsy.test.security;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lsy.test.security.code.ValidateCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Gson gson = new Gson();

    @Test
    void contextLoads() {
        ValidateCode code = new ValidateCode();
        code.setCode("2232");
        code.setExpiredMinute(1);
        code.setExpiredTime(LocalDateTime.now().plusMinutes(1L));
        Type type = new TypeToken<ValidateCode>(){}.getType();
        String str = gson.toJson(code, type);
        System.out.println(str);
        redisTemplate.opsForValue().set("test", str);
    }

}
