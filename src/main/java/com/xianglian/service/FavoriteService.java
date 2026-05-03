package com.xianglian.service;

import com.xianglian.pojo.Favorite;
import com.xianglian.pojo.Post;
import com.xianglian.pojo.Policy;

import java.util.List;

public interface FavoriteService {
    void addFavorite(Favorite favorite);
    void removeFavorite(Integer id);
    void removeBatchFavorites(String ids);
    List<Favorite> getMyFavorites(Integer userId);
    List<Favorite> getMyFavoritesByType(Integer userId, String targetType);
    boolean isFavorite(Integer userId, Integer postId);
    boolean isFavoriteByTarget(Integer userId, String targetType, Integer targetId);
    void removeFavoriteByPostId(Integer userId, Integer postId);
    void removeFavoriteByTarget(Integer userId, String targetType, Integer targetId);
    List<Post> getMyFavoritePosts(Integer userId);
    List<Policy> getMyFavoritePolicies(Integer userId);
    Integer getFavoriteCount(Integer userId);
    Integer getFavoriteCountByType(Integer userId, String targetType);
}