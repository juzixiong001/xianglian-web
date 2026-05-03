package com.xianglian.controller;

import com.github.pagehelper.PageInfo;
import com.xianglian.pojo.Post;
import com.xianglian.pojo.dto.PostCreateDTO;
import com.xianglian.service.PostService;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "帖子模块", description = "帖子相关接口")
public class PostController {
    @Autowired
    private PostService postService;

    @Operation(summary = "获取所有帖子")
    @GetMapping
    public Result<Map<String, Object>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        Map<String, Object> data = new HashMap<>();
        data.put("total", posts.size());
        data.put("list", posts);
        return Result.success(data);
    }

    @Operation(summary = "创建帖子")
    @PostMapping
    public Result<Post> createPost(@RequestBody PostCreateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Post post = new Post();
        post.setUserId(userId.intValue());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setType(dto.getType());
        post.setArea(dto.buildArea());
        post.setPrice(dto.getPrice());
        post.setContact(dto.getContact());
        post.setImages(dto.getImages());
        postService.createPost(post);
        return Result.success(post);
    }

    @Operation(summary = "删除帖子")
    @DeleteMapping("/{id}")
    public Result<Map<String, Object>> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("message", "删除成功");
        return Result.success(data);
    }

    @Operation(summary = "批量删除帖子")
    @DeleteMapping
    public Result<Map<String, Object>> deleteBatchPosts(@RequestParam("ids") String ids) {
        postService.deleteBatchPosts(ids);
        int deletedCount = ids.split(",").length;
        Map<String, Object> data = new HashMap<>();
        data.put("ids", ids);
        data.put("deletedCount", deletedCount);
        data.put("message", "批量删除成功");
        return Result.success(data);
    }

    @Operation(summary = "获取我的帖子")
    @GetMapping("/my")
    public Result<Map<String, Object>> getMyPosts(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Post> posts = postService.getMyPosts(userId.intValue());
        Map<String, Object> data = new HashMap<>();
        data.put("total", posts.size());
        data.put("list", posts);
        return Result.success(data);
    }

    @Operation(summary = "获取我的帖子(兼容旧路径)")
    @GetMapping("/user/publishes")
    public Result<Map<String, Object>> getMyPostsOld(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<Post> posts = postService.getMyPosts(userId.intValue());
        Map<String, Object> data = new HashMap<>();
        data.put("total", posts.size());
        data.put("list", posts);
        return Result.success(data);
    }

    @Operation(summary = "搜索帖子")
    @GetMapping("/search")
    public Result<Map<String, Object>> searchPosts(@RequestParam(required = false) String title,
                            @RequestParam(required = false) String content,
                            @RequestParam(required = false) String type,
                            @RequestParam(required = false) String province,
                            @RequestParam(required = false) String city,
                            @RequestParam(required = false, defaultValue = "time_desc") String sort,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "10") Integer size) {
        PageInfo<Post> pageInfo = postService.searchPosts(title, content, type, province, city, sort, page, size);
        Map<String, Object> data = new HashMap<>();
        data.put("total", pageInfo.getTotal());
        data.put("list", pageInfo.getList());
        data.put("pageNum", pageInfo.getPageNum());
        data.put("pageSize", pageInfo.getPageSize());
        data.put("pages", pageInfo.getPages());
        return Result.success(data);
    }
}