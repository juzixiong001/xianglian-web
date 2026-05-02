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

    @Operation(summary = "个性化推荐", description = "根据用户收藏或发布过的帖子类型返回同类最新帖子")
    @GetMapping("/personalized")
    public Result<List<Post>> getPersonalizedRecommendations(HttpServletRequest request,
                                                 @RequestParam(defaultValue = "5") Integer limit) {
        Long userId = (Long) request.getAttribute("userId");
        List<Post> recommendations = recommendService.getRecommendations(userId.intValue(), limit);
        return Result.success(recommendations);
    }
}