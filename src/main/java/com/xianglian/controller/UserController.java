package com.xianglian.controller;

import com.xianglian.pojo.User;
import com.xianglian.service.UserService;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "用户模块", description = "用户个人信息管理")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "获取用户个人信息")
    @GetMapping("/profile")
    public Result getProfile(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        User user = userService.getProfile(userId);
        return Result.success(user);
    }

    @Operation(summary = "更新用户个人信息")
    @PutMapping("/profile")
    public Result updateProfile(@RequestBody User user, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        user.setId(userId);
        userService.updateProfile(user);
        User updatedUser = userService.getProfile(userId);
        return Result.success(updatedUser);
    }
}