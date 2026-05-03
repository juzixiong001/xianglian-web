package com.xianglian.controller;

import com.github.pagehelper.PageInfo;
import com.xianglian.pojo.Policy;
import com.xianglian.pojo.dto.PolicyCreateDTO;
import com.xianglian.service.PolicyService;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/policies")
@Tag(name = "政策模块", description = "惠农政策管理接口")
public class PolicyController {
    @Autowired
    private PolicyService policyService;

    @Operation(summary = "获取所有政策列表")
    @GetMapping
    public Result getAllPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        Map<String, Object> data = new HashMap<>();
        data.put("total", policies.size());
        data.put("list", policies);
        return Result.success(data);
    }

    @Operation(summary = "获取政策详情")
    @GetMapping("/{id}")
    public Result getPolicyById(@PathVariable Integer id) {
        Policy policy = policyService.getPolicyById(id);
        return Result.success(policy);
    }

    @Operation(summary = "发布政策")
    @PostMapping
    public Result createPolicy(@RequestBody PolicyCreateDTO dto) {
        Policy policy = new Policy();
        policy.setTitle(dto.getTitle());
        policy.setContent(dto.getContent());
        policy.setType(dto.getType());
        policy.setArea(dto.buildArea());
        policy.setMoney(dto.getMoney());
        policy.setConditions(dto.getConditions());
        policy.setMaterials(dto.getMaterials());
        policy.setProcess(dto.getProcess());
        policy.setContact(dto.getContact());
        policyService.createPolicy(policy);
        return Result.success(policy);
    }

    @Operation(summary = "更新政策信息")
    @PutMapping("/{id}")
    public Result updatePolicy(@PathVariable Integer id, @RequestBody PolicyCreateDTO dto) {
        Policy policy = new Policy();
        policy.setId(id);
        policy.setTitle(dto.getTitle());
        policy.setContent(dto.getContent());
        policy.setType(dto.getType());
        policy.setArea(dto.buildArea());
        policy.setMoney(dto.getMoney());
        policy.setConditions(dto.getConditions());
        policy.setMaterials(dto.getMaterials());
        policy.setProcess(dto.getProcess());
        policy.setContact(dto.getContact());
        policyService.updatePolicy(policy);
        return Result.success(policy);
    }

    @Operation(summary = "删除政策")
    @DeleteMapping("/{id}")
    public Result deletePolicy(@PathVariable Integer id) {
        policyService.deletePolicy(id);
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("message", "删除成功");
        return Result.success(data);
    }

    @Operation(summary = "批量删除政策")
    @DeleteMapping
    public Result deleteBatchPolicies(@RequestParam("ids") String ids) {
        policyService.deleteBatchPolicies(ids);
        int deletedCount = ids.split(",").length;
        Map<String, Object> data = new HashMap<>();
        data.put("ids", ids);
        data.put("deletedCount", deletedCount);
        data.put("message", "批量删除成功");
        return Result.success(data);
    }

    @Operation(summary = "搜索政策")
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