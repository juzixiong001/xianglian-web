package com.xianglian.controller;

import com.xianglian.pojo.Post;
import com.xianglian.service.PostService;
import com.xianglian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public Result getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        Map<String, Object> data = new HashMap<>();
        data.put("total", posts.size());
        data.put("list", posts);
        return Result.success(data);
    }

    @PostMapping
    public Result createPost(@RequestBody Post post, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        post.setUserId(userId);
        postService.createPost(post);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteBatchPosts(@RequestParam("ids") String ids) {
        postService.deleteBatchPosts(ids);
        return Result.success();
    }

    @GetMapping("/user/publishes")
    public Result getMyPosts(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        List<Post> posts = postService.getMyPosts(userId);
        Map<String, Object> data = new HashMap<>();
        data.put("total", posts.size());
        data.put("list", posts);
        return Result.success(data);
    }

    @GetMapping("/search")
    public Result searchPosts(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String content,
                              @RequestParam(required = false) String type,
                              @RequestParam(required = false, defaultValue = "time_desc") String sort,
                              @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "10") Integer size) {
        List<Post> posts = postService.searchPosts(title, content, type, sort, page, size);
        int total = postService.getSearchTotal(title, content, type);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("list", posts);
        return Result.success(data);
    }
}