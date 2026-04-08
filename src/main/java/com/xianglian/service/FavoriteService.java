package com.xianglian.service;

import com.xianglian.pojo.Favorite;

import java.util.List;

public interface FavoriteService {
    void addFavorite(Favorite favorite);
    void removeFavorite(Integer id);
    void removeBatchFavorites(String ids);
    List<Favorite> getMyFavorites(Integer userId);
}