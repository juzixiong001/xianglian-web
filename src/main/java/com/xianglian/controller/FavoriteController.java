package com.xianglian.controller;

import com.xianglian.pojo.Favorite;
import com.xianglian.service.FavoriteService;
import com.xianglian.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

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

    @DeleteMapping("/{id}")
    public Result removeFavorite(@PathVariable Integer id) {
        favoriteService.removeFavorite(id);
        Map<String, String> data = new HashMap<>();
        data.put("message", "删除成功");
        return Result.success(data);
    }

    @DeleteMapping
    public Result removeBatchFavorites(@RequestParam("ids") String ids) {
        favoriteService.removeBatchFavorites(ids);
        // 计算删除数量
        int deletedCount = ids.split(",").length;
        Map<String, Object> data = new HashMap<>();
        data.put("ids", ids);
        data.put("deletedCount", deletedCount);
        data.put("message", "批量删除成功");
        return Result.success(data);
    }

    @GetMapping("/my")
    public Result getMyFavorites(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Favorite> favorites = favoriteService.getMyFavorites(userId.intValue());
        return Result.success(favorites);
    }
}