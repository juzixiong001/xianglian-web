package com.xianglian.service;

import com.xianglian.pojo.User;

public interface UserService {
    User getProfile(Long userId);
    void updateProfile(User user);
    void register(User user);
    User login(String username, String password);
}