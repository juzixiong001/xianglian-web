package com.xianglian.controller;

import com.github.pagehelper.PageInfo;
import com.xianglian.pojo.Post;
import com.xianglian.service.PostService;
import com.xianglian.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
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
        Long userId = (Long) request.getAttribute("userId");
        post.setUserId(userId.intValue());
        postService.createPost(post);
        return Result.success(post);
    }

    @DeleteMapping("/{id}")
    public Result deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("message", "删除成功");
        return Result.success(data);
    }

    @DeleteMapping
    public Result deleteBatchPosts(@RequestParam("ids") String ids) {
        postService.deleteBatchPosts(ids);
        // 计算删除数量
        int deletedCount = ids.split(",").length;
        Map<String, Object> data = new HashMap<>();
        data.put("ids", ids);
        data.put("deletedCount", deletedCount);
        data.put("message", "批量删除成功");
        return Result.success(data);
    }

    @GetMapping("/my")
    public Result getMyPosts(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Post> posts = postService.getMyPosts(userId.intValue());
        Map<String, Object> data = new HashMap<>();
        data.put("total", posts.size());
        data.put("list", posts);
        return Result.success(data);
    }

    // 兼容旧路径
    @GetMapping("/user/publishes")
    public Result getMyPostsOld(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Post> posts = postService.getMyPosts(userId.intValue());
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
        PageInfo<Post> pageInfo = postService.searchPosts(title, content, type, sort, page, size);
        Map<String, Object> data = new HashMap<>();
        data.put("total", pageInfo.getTotal());
        data.put("list", pageInfo.getList());
        data.put("pageNum", pageInfo.getPageNum());
        data.put("pageSize", pageInfo.getPageSize());
        data.put("pages", pageInfo.getPages());
        return Result.success(data);
    }
}