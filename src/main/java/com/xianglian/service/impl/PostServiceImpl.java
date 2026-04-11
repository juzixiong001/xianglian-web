package com.xianglian.service.impl;

import com.github.pagehelper.PageHelper;
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

    @Override
    public List<Post> searchPosts(String title, String content, String type, String sort, Integer page, Integer size) {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        PageHelper.startPage(page, size);
        return postMapper.searchPosts(title, content, type, sort);
    }

    @Override
    public int getSearchTotal(String title, String content, String type) {
        // 这里简化处理，实际项目中应该实现一个专门的count查询
        List<Post> posts = postMapper.searchPosts(title, content, type, null);
        return posts.size();
    }
}