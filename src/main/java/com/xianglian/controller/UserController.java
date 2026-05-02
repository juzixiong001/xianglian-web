package com.xianglian.controller;

import com.xianglian.pojo.Favorite;
import com.xianglian.pojo.Post;
import com.xianglian.pojo.User;
import com.xianglian.service.FavoriteService;
import com.xianglian.service.UserService;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@Tag(name = "用户模块", description = "用户个人信息管理")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;

    @Operation(summary = "获取用户个人信息")
    @GetMapping("/profile")
    public Result<User> getProfile(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        User user = userService.getProfile(userId);
        return Result.success(user);
    }

    @Operation(summary = "更新用户个人信息")
    @PutMapping("/profile")
    public Result<User> updateProfile(@RequestBody User user, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        user.setId(userId);
        userService.updateProfile(user);
        User updatedUser = userService.getProfile(userId);
        return Result.success(updatedUser);
    }

    @Operation(summary = "我的收藏列表", description = "返回用户收藏的帖子列表，包含分页信息")
    @GetMapping("/favorites")
    public Result<Map<String, Object>> getMyFavorites(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Favorite> favorites = favoriteService.getMyFavorites(userId.intValue());
        List<Post> posts = favoriteService.getMyFavoritePosts(userId.intValue());
        
        List<Map<String, Object>> resultList = favorites.stream()
                .map(favorite -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("favoriteId", favorite.getId());
                    Post post = posts.stream()
                            .filter(p -> p.getId().equals(favorite.getPostId()))
                            .findFirst()
                            .orElse(null);
                    item.put("post", post);
                    return item;
                })
                .collect(Collectors.toList());
        
        Map<String, Object> data = new HashMap<>();
        data.put("total", favorites.size());
        data.put("list", resultList);
        return Result.success(data);
    }
}