package com.xianglian.controller;

import com.xianglian.pojo.User;
import com.xianglian.service.UserService;
import com.xianglian.utils.JwtUtils;
import com.xianglian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // 参数校验
        if (username == null || username.isEmpty()) {
            return Result.error(400, "用户名不能为空");
        }
        if (password == null || password.isEmpty()) {
            return Result.error(400, "密码不能为空");
        }

        User user = userService.login(username, password);
        if (user != null) {
            String token = JwtUtils.generateToken(user.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", user);
            return Result.success(data);
        }
        return Result.error(401, "用户名或密码错误");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        // 参数校验
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return Result.error(400, "用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return Result.error(400, "密码不能为空");
        }
        if (user.getNickname() == null || user.getNickname().isEmpty()) {
            return Result.error(400, "昵称不能为空");
        }

        try {
            userService.register(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error(500, "注册失败");
        }
    }
}