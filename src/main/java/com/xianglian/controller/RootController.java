package com.xianglian.controller;

import com.xianglian.pojo.User;
import com.xianglian.service.UserService;
import com.xianglian.utils.JwtUtils;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "根路径接口", description = "兼容旧路径的接口")
public class RootController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Operation(summary = "登录(根路径)")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            String token = jwtUtils.generateToken(loginUser.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", loginUser);
            return Result.success(data);
        } else {
            return Result.error(401, "用户名或密码错误");
        }
    }

    @Operation(summary = "注册(根路径)")
    @PostMapping("/register")
    public Result<Map<String, Object>> register(@RequestBody User user) {
        userService.register(user);
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            String token = jwtUtils.generateToken(loginUser.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", loginUser);
            return Result.success(data);
        }
        return Result.success();
    }
}