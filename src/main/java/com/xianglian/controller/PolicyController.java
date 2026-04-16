package com.xianglian.controller;

import com.github.pagehelper.PageInfo;
import com.xianglian.pojo.Policy;
import com.xianglian.service.PolicyService;
import com.xianglian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    // 获取政策列表
    @GetMapping
    public Result getAllPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        Map<String, Object> data = new HashMap<>();
        data.put("total", policies.size());
        data.put("list", policies);
        return Result.success(data);
    }

    // 获取政策详情
    @GetMapping("/{id}")
    public Result getPolicyById(@PathVariable Integer id) {
        Policy policy = policyService.getPolicyById(id);
        return Result.success(policy);
    }

    // 发布政策
    @PostMapping
    public Result createPolicy(@RequestBody Policy policy) {
        policyService.createPolicy(policy);
        return Result.success(policy);
    }

    @PutMapping("/{id}")
    public Result updatePolicy(@PathVariable Integer id, @RequestBody Policy policy) {
        policy.setId(id);
        policyService.updatePolicy(policy);
        return Result.success(policy);
    }

    @DeleteMapping("/{id}")
    public Result deletePolicy(@PathVariable Integer id) {
        policyService.deletePolicy(id);
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("message", "删除成功");
        return Result.success(data);
    }

    @DeleteMapping
    public Result deleteBatchPolicies(@RequestParam("ids") String ids) {
        policyService.deleteBatchPolicies(ids);
        // 计算删除数量
        int deletedCount = ids.split(",").length;
        Map<String, Object> data = new HashMap<>();
        data.put("ids", ids);
        data.put("deletedCount", deletedCount);
        data.put("message", "批量删除成功");
        return Result.success(data);
    }

    // 搜索政策
    @GetMapping("/search")
    public Result searchPolicies(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String type,
                              @RequestParam(required = false) String area,
                              @RequestParam(required = false, defaultValue = "time_desc") String sort,
                              @RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = "10") Integer size) {
        PageInfo<Policy> pageInfo = policyService.searchPolicies(title, type, area, sort, page, size);
        Map<String, Object> data = new HashMap<>();
        data.put("total", pageInfo.getTotal());
        data.put("list", pageInfo.getList());
        data.put("pageNum", pageInfo.getPageNum());
        data.put("pageSize", pageInfo.getPageSize());
        data.put("pages", pageInfo.getPages());
        return Result.success(data);
    }
}