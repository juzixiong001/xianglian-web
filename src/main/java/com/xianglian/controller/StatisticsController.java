package com.xianglian.controller;

import com.xianglian.mapper.FavoriteMapper;
import com.xianglian.mapper.PostMapper;
import com.xianglian.mapper.UserMapper;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@Tag(name = "统计模块", description = "数据统计与分析接口")
public class StatisticsController {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Operation(summary = "获取数据概览")
    @GetMapping("/overview")
    public Result getOverview() {
        Map<String, Object> overview = new HashMap<>();
        
        Integer postCount = postMapper.countAll();
        Integer userCount = userMapper.countAll();
        
        List<Map<String, Object>> typeCount = postMapper.countByType();
        int supplyCount = 0;
        int demandCount = 0;
        for (Map<String, Object> item : typeCount) {
            String type = (String) item.get("type");
            Integer count = (Integer) item.get("count");
            if ("supply".equals(type) || "供应".equals(type)) {
                supplyCount = count;
            } else if ("demand".equals(type) || "需求".equals(type)) {
                demandCount = count;
            }
        }
        
        overview.put("totalPosts", postCount);
        overview.put("totalUsers", userCount);
        overview.put("supplyCount", supplyCount);
        overview.put("demandCount", demandCount);
        
        return Result.success(overview);
    }

    @Operation(summary = "获取帖子类型分布")
    @GetMapping("/posts/type")
    public Result getPostTypeDistribution() {
        List<Map<String, Object>> distribution = postMapper.countByType();
        return Result.success(distribution);
    }

    @Operation(summary = "获取帖子地区分布")
    @GetMapping("/posts/area")
    public Result getPostAreaDistribution() {
        List<Map<String, Object>> distribution = postMapper.countByArea();
        return Result.success(distribution);
    }

    @Operation(summary = "获取帖子7天趋势")
    @GetMapping("/posts/trend")
    public Result getPostTrendLast7Days() {
        List<Map<String, Object>> trend = postMapper.countByDateLast7Days();
        return Result.success(trend);
    }

    @Operation(summary = "获取用户30天趋势")
    @GetMapping("/users/trend")
    public Result getUserTrendLast30Days() {
        List<Map<String, Object>> trend = userMapper.countByDateLast30Days();
        return Result.success(trend);
    }

    @Operation(summary = "获取所有图表数据")
    @GetMapping("/charts")
    public Result getAllChartsData() {
        Map<String, Object> charts = new HashMap<>();
        
        charts.put("overview", getOverview().getData());
        charts.put("postTypeDistribution", getPostTypeDistribution().getData());
        charts.put("postAreaDistribution", getPostAreaDistribution().getData());
        charts.put("postTrend", getPostTrendLast7Days().getData());
        charts.put("userTrend", getUserTrendLast30Days().getData());
        
        return Result.success(charts);
    }
}