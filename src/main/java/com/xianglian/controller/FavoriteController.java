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
        
        Integer postId = (Integer) requestBody.get("postId");
        
        Favorite favorite = new Favorite();
        favorite.setUserId(userId.intValue());
        
        if (targetType != null && targetId != null) {
            favorite.setTargetType(targetType);
            favorite.setTargetId(targetId);
            if ("post".equals(targetType)) {
                favorite.setPostId(targetId);
            }
        } else if (postId != null) {
            favorite.setPostId(postId);
            favorite.setTargetType("post");
            favorite.setTargetId(postId);
        }
        
        favoriteService.addFavorite(favorite);
        return Result.success(favorite);
    }

    @Operation(summary = "取消单个收藏")
    @DeleteMapping("/{id}")
    public Result<Map<String, String>> removeFavorite(@PathVariable Integer id) {
        favoriteService.removeFavorite(id);
        Map<String, String> data = new HashMap<>();
        data.put("message", "删除成功");
        return Result.success(data);
    }

    @Operation(summary = "批量取消收藏")
    @DeleteMapping
    public Result<Map<String, Object>> removeBatchFavorites(@RequestParam("ids") String ids) {
        favoriteService.removeBatchFavorites(ids);
        int deletedCount = ids.split(",").length;
        Map<String, Object> data = new HashMap<>();
        data.put("ids", ids);
        data.put("deletedCount", deletedCount);
        data.put("message", "批量删除成功");
        return Result.success(data);
    }

    @Operation(summary = "根据目标类型和ID取消收藏")
    @DeleteMapping("/target")
    public Result<Map<String, String>> removeFavoriteByTarget(
            @RequestParam String targetType,
            @RequestParam Integer targetId,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        favoriteService.removeFavoriteByTarget(userId.intValue(), targetType, targetId);
        Map<String, String> data = new HashMap<>();
        data.put("message", "删除成功");
        return Result.success(data);
    }

    @Operation(summary = "检查是否已收藏")
    @GetMapping("/check")
    public Result<Map<String, Object>> checkFavorite(
            @RequestParam(required = false) Integer postId,
            @RequestParam(required = false) String targetType,
            @RequestParam(required = false) Integer targetId,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        boolean isFavorited = false;
        
        if (targetType != null && targetId != null) {
            isFavorited = favoriteService.isFavoriteByTarget(userId.intValue(), targetType, targetId);
        } else if (postId != null) {
            isFavorited = favoriteService.isFavorite(userId.intValue(), postId);
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("isFavorited", isFavorited);
        return Result.success(data);
    }

    @Operation(summary = "获取我的收藏列表")
    @GetMapping("/my")
    public Result<List<Favorite>> getMyFavorites(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Favorite> favorites = favoriteService.getMyFavorites(userId.intValue());
        return Result.success(favorites);
    }

    @Operation(summary = "获取收藏数量")
    @GetMapping("/count")
    public Result<Map<String, Object>> getFavoriteCount(
            @RequestParam(required = false) String type,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Integer count;
        if (type != null && !type.isEmpty()) {
            count = favoriteService.getFavoriteCountByType(userId.intValue(), type);
        } else {
            count = favoriteService.getFavoriteCount(userId.intValue());
        }
        
        Map<String, Object> data = new HashMap<>();
        data.put("count", count != null ? count : 0);
        return Result.success(data);
    }

    @Operation(summary = "获取我收藏的帖子")
    @GetMapping("/my/posts")
    public Result<List<Post>> getMyFavoritePosts(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Post> posts = favoriteService.getMyFavoritePosts(userId.intValue());
        return Result.success(posts);
    }

    @Operation(summary = "获取我收藏的政策")
    @GetMapping("/my/policies")
    public Result<List<Policy>> getMyFavoritePolicies(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Policy> policies = favoriteService.getMyFavoritePolicies(userId.intValue());
        return Result.success(policies);
    }
}