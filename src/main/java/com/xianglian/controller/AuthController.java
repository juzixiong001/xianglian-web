package com.xianglian.controller;

import com.xianglian.pojo.User;
import com.xianglian.service.UserService;
import com.xianglian.utils.JwtUtils;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "认证模块", description = "用户登录注册接口")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Operation(summary = "用户注册", description = "注册参数：用户名、电话、密码")
    @PostMapping("/register")
    public Result<Map<String, Object>> register(@RequestBody User user) {
        userService.register(user);
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            String token = jwtUtils.generateToken(loginUser.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", buildUserResponse(loginUser));
            return Result.success("注册成功", data);
        }
        return Result.success("注册成功", new HashMap<>());
    }

    @Operation(summary = "用户登录", description = "登录参数：用户名+密码，也支持手机号+密码")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            String token = jwtUtils.generateToken(loginUser.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", buildUserResponse(loginUser));
            return Result.success("登录成功", data);
        } else {
            return Result.error(401, "用户名/手机号或密码错误");
        }
    }

    @Operation(summary = "发送短信验证码")
    @PostMapping("/send-sms-code")
    public Result<Map<String, Object>> sendSmsCode(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String code = userService.sendSmsCode(phone);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "验证码发送成功");
        data.put("code", code);
        return Result.success(data);
    }

    @Operation(summary = "手机号注册（验证码）")
    @PostMapping("/register/phone")
    public Result<Map<String, Object>> registerByPhone(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String code = request.get("code");
        
        if (!userService.verifySmsCode(phone, code)) {
            return Result.error(400, "验证码错误或已过期");
        }
        
        User user = userService.registerByPhone(phone);
        String token = jwtUtils.generateToken(user.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", buildUserResponse(user));
        data.put("defaultPassword", "123456");
        return Result.success("注册成功，初始密码为123456", data);
    }

    @Operation(summary = "手机号登录（验证码）")
    @PostMapping("/login/phone")
    public Result<Map<String, Object>> loginByPhone(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String code = request.get("code");
        
        if (!userService.verifySmsCode(phone, code)) {
            return Result.error(400, "验证码错误或已过期");
        }
        
        User user = userService.loginByPhone(phone, "123456");
        if (user != null) {
            String token = jwtUtils.generateToken(user.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", buildUserResponse(user));
            return Result.success("登录成功", data);
        } else {
            return Result.error(401, "该手机号尚未注册");
        }
    }

    private Map<String, Object> buildUserResponse(User user) {
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("id", user.getId());
        userResponse.put("username", user.getUsername());
        userResponse.put("nickname", user.getNickname());
        userResponse.put("avatar", user.getAvatar());
        userResponse.put("phone", user.getPhone());
        userResponse.put("email", user.getEmail());
        userResponse.put("createTime", user.getCreateTime());
        userResponse.put("updateTime", user.getUpdateTime());
        return userResponse;
    }
}