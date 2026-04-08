package com.xianglian.service;

import com.xianglian.pojo.User;

public interface UserService {
    User getProfile(Integer userId);
    void updateProfile(User user);
}
