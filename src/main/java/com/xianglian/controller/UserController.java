package com.xianglian.controller;

import com.xianglian.pojo.User;
import com.xianglian.service.UserService;
import com.xianglian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public Result getProfile(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        User user = userService.getProfile(userId);
        return Result.success(user);
    }

    @PutMapping("/profile")
    public Result updateProfile(@RequestBody User user, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        user.setId(userId);
        userService.updateProfile(user);
        return Result.success();
    }
}
