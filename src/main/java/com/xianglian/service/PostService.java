package com.xianglian.service;

import com.github.pagehelper.PageInfo;
import com.xianglian.pojo.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    void createPost(Post post);
    void deletePost(Integer id);
    void deleteBatchPosts(String ids);
    List<Post> getMyPosts(Integer userId);
    PageInfo<Post> searchPosts(String title, String content, String type, String sort, Integer page, Integer size);
}