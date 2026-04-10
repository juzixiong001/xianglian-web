package com.xianglian.service.impl;

import com.xianglian.mapper.PolicyMapper;
import com.xianglian.pojo.Policy;
import com.xianglian.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyMapper policyMapper;


    @Override
    public List<Policy> getAllPolicies() {
        return policyMapper.findAll();
    }

    @Override
    public Policy getPolicyById(Integer id) {
        return policyMapper.findById(id);
    }

    @Override
    @Transactional
    public void createPolicy(Policy policy) {
        policyMapper.insert(policy);
    }

    @Override
    @Transactional
    public void updatePolicy(Policy policy) {
        policyMapper.update(policy);
    }

    @Override
    @Transactional
    public void deletePolicy(Integer id) {
        policyMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteBatchPolicies(String ids) {
        policyMapper.deleteBatch(ids);
    }
}
