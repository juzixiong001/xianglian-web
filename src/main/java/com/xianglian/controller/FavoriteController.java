package com.xianglian.controller;

import com.xianglian.pojo.Favorite;
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
@Tag(name = "收藏模块", description = "用户收藏帖子管理")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Operation(summary = "添加收藏")
    @PostMapping
    public Result addFavorite(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Integer postId = (Integer) requestBody.get("postId");
        
        Favorite favorite = new Favorite();
        favorite.setUserId(userId.intValue());
        favorite.setPostId(postId);
        
        favoriteService.addFavorite(favorite);
        return Result.success(favorite);
    }

    @Operation(summary = "取消收藏")
    @DeleteMapping("/{id}")
    public Result removeFavorite(@PathVariable Integer id) {
        favoriteService.removeFavorite(id);
        Map<String, String> data = new HashMap<>();
        data.put("message", "删除成功");
        return Result.success(data);
    }

    @Operation(summary = "根据帖子ID取消收藏")
    @DeleteMapping("/post/{postId}")
    public Result removeFavoriteByPostId(@PathVariable Integer postId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        favoriteService.removeFavoriteByPostId(userId.intValue(), postId);
        Map<String, String> data = new HashMap<>();
        data.put("message", "取消收藏成功");
        return Result.success(data);
    }

    @Operation(summary = "批量取消收藏")
    @DeleteMapping
    public Result removeBatchFavorites(@RequestParam("ids") String ids) {
        favoriteService.removeBatchFavorites(ids);
        int deletedCount = ids.split(",").length;
        Map<String, Object> data = new HashMap<>();
        data.put("ids", ids);
        data.put("deletedCount", deletedCount);
        data.put("message", "批量删除成功");
        return Result.success(data);
    }

    @Operation(summary = "获取我的收藏列表")
    @GetMapping("/my")
    public Result getMyFavorites(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Favorite> favorites = favoriteService.getMyFavorites(userId.intValue());
        return Result.success(favorites);
    }

    @Operation(summary = "获取我收藏的帖子")
    @GetMapping("/my/posts")
    public Result getMyFavoritePosts(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Post> posts = favoriteService.getMyFavoritePosts(userId.intValue());
        return Result.success(posts);
    }

    @Operation(summary = "检查是否已收藏")
    @GetMapping("/check")
    public Result checkFavorite(@RequestParam Integer postId, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        boolean isFavorite = favoriteService.isFavorite(userId.intValue(), postId);
        Map<String, Object> data = new HashMap<>();
        data.put("isFavorite", isFavorite);
        return Result.success(data);
    }

    @Operation(summary = "获取收藏数量")
    @GetMapping("/count")
    public Result getFavoriteCount(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Integer count = favoriteService.getFavoriteCount(userId.intValue());
        Map<String, Object> data = new HashMap<>();
        data.put("count", count);
        return Result.success(data);
    }
}