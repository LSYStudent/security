package com.lsy.test.security.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.google.gson.reflect.TypeToken;
import com.lsy.test.security.code.ValidateCodeProcessorHolder;
import com.lsy.test.security.config.R;
import com.lsy.test.security.model.User;
import com.lsy.test.security.service.UserService;
import com.lsy.test.security.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * user Controller层
 * @author lsy
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService targetService;

    private final ValidateCodeProcessorHolder holder;

    @Autowired
    public UserController(UserService targetService, ValidateCodeProcessorHolder holder) {
        this.targetService = targetService;
        this.holder = holder;
    }

    @RequestMapping(value = "/list")
    public R<List<User>> list() {
        return R.ok(targetService.list());
    }

    @RequestMapping(value = "/find")
    public R<User> findUser(String name) {
        return R.ok(targetService.findUser(name));
    }

    public R<Boolean> save(@RequestBody User entity) {
        return R.ok(targetService.save(entity.defv()));
    }

    @GetMapping(value = "/create/code")
    public void imgCode(HttpServletResponse response) throws IOException {
        /*response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");*/
        //response.setDateHeader("Expire", 0);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(95, 25, 4, 35);

        ImageIO.write(lineCaptcha.getImage(), "JPEG", response.getOutputStream());
    }

    @GetMapping(value = "/code/{type}")
    public void code(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IOException {
        holder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

    @GetMapping(value = "test/cache")
    public void test() {
        Type type = new TypeToken<String>() {}.getType();
        RedisUtils.set("test", "的大师傅但是", type);
    }

}
