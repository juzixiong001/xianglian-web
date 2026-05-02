package com.xianglian.controller;

import com.xianglian.pojo.Favorite;
import com.xianglian.service.FavoriteService;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@Tag(name = "收藏模块", description = "用户收藏帖子管理")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Operation(summary = "添加收藏")
    @PostMapping
    public Result<Favorite> addFavorite(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Integer postId = (Integer) requestBody.get("postId");
        
        Favorite favorite = new Favorite();
        favorite.setUserId(userId.intValue());
        favorite.setPostId(postId);
        
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
}