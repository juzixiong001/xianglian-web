package com.xianglian.controller;

import com.xianglian.pojo.Favorite;
import com.xianglian.pojo.Policy;
import com.xianglian.pojo.Post;
import com.xianglian.service.FavoriteService;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@Tag(name = "收藏模块", description = "用户收藏管理")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Operation(summary = "添加收藏")
    @PostMapping
    public Result<Favorite> addFavorite(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        
        String targetType = (String) requestBody.get("targetType");
        Integer targetId = (Integer) requestBody.get("targetId");
        
        Favorite favorite = new Favorite();
        favorite.setUserId(userId.intValue());
        
        if (targetType != null && targetId != null) {
            if ("post".equals(targetType) || "policy".equals(targetType)) {
                favorite.setTargetType(targetType);
                favorite.setTargetId(targetId);
            } else {
                return Result.error("不支持的收藏类型：" + targetType + "，仅支持 post 和 policy");
            }
        } else {
            return Result.error("缺少必要参数：请提供 {targetType, targetId}");
        }
        
        try {
            favoriteService.addFavorite(favorite);
            return Result.success(favorite);
        } catch (Exception e) {
            return Result.error("添加收藏失败：" + e.getMessage());
        }
    }

    @Operation(summary = "取消单个收藏")
    @DeleteMapping("/{id}")
    public Result<Map<String, String>> removeFavorite(@PathVariable Integer id) {
        try {
            favoriteService.removeFavorite(id);
            Map<String, String> data = new HashMap<>();
            data.put("message", "删除成功");
            return Result.success(data);
        } catch (Exception e) {
            return Result.error("删除收藏失败：" + e.getMessage());
        }
    }

    @Operation(summary = "根据目标类型和ID取消收藏")
    @DeleteMapping("/target")
    public Result<Map<String, String>> removeFavoriteByTarget(
            @RequestParam String targetType,
            @RequestParam Integer targetId,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        try {
            favoriteService.removeFavoriteByTarget(userId.intValue(), targetType, targetId);
            Map<String, String> data = new HashMap<>();
            data.put("message", "删除成功");
            return Result.success(data);
        } catch (Exception e) {
            return Result.error("删除收藏失败：" + e.getMessage());
        }
    }

    @Operation(summary = "检查是否已收藏")
    @GetMapping("/check")
    public Result<Map<String, Object>> checkFavorite(
            @RequestParam String targetType,
            @RequestParam Integer targetId,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        
        boolean isFavorited = favoriteService.isFavoriteByTarget(userId.intValue(), targetType, targetId);
        
        Map<String, Object> data = new HashMap<>();
        data.put("isFavorited", isFavorited);
        return Result.success(data);
    }

    @Operation(summary = "获取我的收藏列表")
    @GetMapping("/my")
    public Result<List<Favorite>> getMyFavorites(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        try {
            List<Favorite> favorites = favoriteService.getMyFavorites(userId.intValue());
            return Result.success(favorites);
        } catch (Exception e) {
            return Result.error("获取收藏列表失败：" + e.getMessage());
        }
    }

    @Operation(summary = "获取我收藏的帖子")
    @GetMapping("/my/posts")
    public Result<List<Post>> getMyFavoritePosts(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        try {
            List<Post> posts = favoriteService.getMyFavoritePosts(userId.intValue());
            return Result.success(posts);
        } catch (Exception e) {
            return Result.error("获取收藏帖子失败：" + e.getMessage());
        }
    }

    @Operation(summary = "获取我收藏的政策")
    @GetMapping("/my/policies")
    public Result<List<Policy>> getMyFavoritePolicies(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        try {
            List<Policy> policies = favoriteService.getMyFavoritePolicies(userId.intValue());
            return Result.success(policies);
        } catch (Exception e) {
            return Result.error("获取收藏政策失败：" + e.getMessage());
        }
    }
}