package com.xianglian.service;

import com.github.pagehelper.PageInfo;
import com.xianglian.pojo.Policy;

import java.util.List;

public interface PolicyService {
    List<Policy> getAllPolicies();
    Policy getPolicyById(Integer id);
    void createPolicy(Policy policy);
    void updatePolicy(Policy policy);
    void deletePolicy(Integer id);
    void deleteBatchPolicies(String ids);
    PageInfo<Policy> searchPolicies(String title, String type, String area, String sort, Integer page, Integer size);
}