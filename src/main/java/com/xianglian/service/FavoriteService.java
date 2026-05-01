package com.xianglian.service;

import com.xianglian.pojo.Favorite;
import com.xianglian.pojo.Post;

import java.util.List;

public interface FavoriteService {
    void addFavorite(Favorite favorite);
    void removeFavorite(Integer id);
    void removeBatchFavorites(String ids);
    List<Favorite> getMyFavorites(Integer userId);
    boolean isFavorite(Integer userId, Integer postId);
    void removeFavoriteByPostId(Integer userId, Integer postId);
    List<Post> getMyFavoritePosts(Integer userId);
    Integer getFavoriteCount(Integer userId);
}