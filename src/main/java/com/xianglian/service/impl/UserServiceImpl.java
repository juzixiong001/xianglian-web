package com.xianglian.service.impl;

import com.xianglian.mapper.UserMapper;
import com.xianglian.pojo.User;
import com.xianglian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getProfile(Integer userId) {
        return userMapper.findById(userId);
    }

    @Override
    @Transactional
    public void updateProfile(User user) {
        userMapper.update(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    @Transactional
    public void register(User user) {
        userMapper.insert(user);
    }
}
