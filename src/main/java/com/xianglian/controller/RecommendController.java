package com.xianglian.controller;

import com.xianglian.pojo.Post;
import com.xianglian.service.RecommendService;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@Tag(name = "推荐模块", description = "帖子推荐接口")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @Operation(summary = "获取用户推荐帖子")
    @GetMapping("/{userId}")
    public Result getRecommendations(@PathVariable Integer userId, 
                                     @RequestParam(defaultValue = "10") Integer limit) {
        List<Post> recommendations = recommendService.getRecommendations(userId, limit);
        return Result.success(recommendations);
    }

    @Operation(summary = "获取热门帖子")
    @GetMapping("/hot")
    public Result getHotPosts(@RequestParam(defaultValue = "10") Integer limit) {
        List<Post> hotPosts = recommendService.getHotPosts(limit);
        return Result.success(hotPosts);
    }

    @Operation(summary = "获取个性化推荐")
    @GetMapping("/personalized")
    public Result getPersonalizedRecommendations(HttpServletRequest request,
                                                 @RequestParam(defaultValue = "10") Integer limit) {
        Long userId = (Long) request.getAttribute("userId");
        List<Post> recommendations = recommendService.getRecommendations(userId.intValue(), limit);
        return Result.success(recommendations);
    }
}