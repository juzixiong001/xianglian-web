package com.xianglian.controller;

import com.xianglian.pojo.Policy;
import com.xianglian.service.PolicyService;
import com.xianglian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/policies")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    // 获取政策列表
    @GetMapping
    public Result getAllPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        return Result.success(policies);
    }

    //// 获取政策详情
    @GetMapping("/{id}")
    public Result getPolicyById(@PathVariable Integer id) {
        Policy policy = policyService.getPolicyById(id);
        return Result.success(policy);
    }

    // 发布政策
    @PostMapping
    public Result createPolicy(@RequestBody Policy policy) {
        policyService.createPolicy(policy);
        return Result.success();
    }

    // 修改政策
    @PutMapping("/{id}")
    public Result updatePolicy(@PathVariable Integer id, @RequestBody Policy policy) {
        policy.setId(id);
        policyService.updatePolicy(policy);
        return Result.success();
    }

    // 删除单条政策
    @DeleteMapping("/{id}")
    public Result deletePolicy(@PathVariable Integer id) {
        policyService.deletePolicy(id);
        return Result.success();
    }

    // 批量删除政策
    @DeleteMapping
    public Result deleteBatchPolicies(@RequestParam("ids") String ids) {
        policyService.deleteBatchPolicies(ids);
        return Result.success();
    }


}
