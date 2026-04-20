package com.xianglian.controller;

import com.xianglian.pojo.User;
import com.xianglian.service.UserService;
import com.xianglian.utils.JwtUtils;
import com.xianglian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            String token = JwtUtils.generateToken(loginUser.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", loginUser);
            return Result.success(data);
        } else {
            return Result.error(401, "用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.register(user);
        // 注册成功后生成token
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            String token = JwtUtils.generateToken(loginUser.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", loginUser);
            return Result.success(data);
        }
        return Result.success();
    }
}