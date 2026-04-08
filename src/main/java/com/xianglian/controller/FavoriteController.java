package com.xianglian.controller;

import com.xianglian.pojo.Favorite;
import com.xianglian.service.FavoriteService;
import com.xianglian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public Result addFavorite(@RequestBody Favorite favorite, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        favorite.setUserId(userId);
        favoriteService.addFavorite(favorite);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result removeFavorite(@PathVariable Integer id) {
        favoriteService.removeFavorite(id);
        return Result.success();
    }

    @DeleteMapping
    public Result removeBatchFavorites(@RequestParam("ids") String ids) {
        favoriteService.removeBatchFavorites(ids);
        return Result.success();
    }

    @GetMapping("/user/favorites")
    public Result getMyFavorites(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        List<Favorite> favorites = favoriteService.getMyFavorites(userId);
        return Result.success(favorites);
    }
}
