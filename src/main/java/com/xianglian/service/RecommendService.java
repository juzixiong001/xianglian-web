package com.xianglian.service;

import com.xianglian.pojo.Post;

import java.util.List;

public interface RecommendService {
    List<Post> getRecommendations(Integer userId, Integer limit);
    List<Post> getHotPosts(Integer limit);
}