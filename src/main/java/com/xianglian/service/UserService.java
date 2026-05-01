package com.xianglian.service;

import com.xianglian.pojo.User;

public interface UserService {
    User getProfile(Long userId);
    void updateProfile(User user);
    void register(User user);
    User login(String username, String password);
    User loginByPhone(String phone, String password);
    User registerByPhone(String phone);
    String sendSmsCode(String phone);
    boolean verifySmsCode(String phone, String code);
}