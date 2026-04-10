package com.xianglian.service.impl;

import com.xianglian.mapper.PostMapper;
import com.xianglian.pojo.Post;
import com.xianglian.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> getAllPosts() {
        return postMapper.findAll();
    }

    @Override
    @Transactional
    public void createPost(Post post) {
        postMapper.insert(post);
    }

    @Override
    @Transactional
    public void deletePost(Integer id) {
        postMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteBatchPosts(String ids) {
        postMapper.deleteBatch(ids);
    }

    @Override
    public List<Post> getMyPosts(Integer userId) {
        return postMapper.findByUserId(userId);
    }
}