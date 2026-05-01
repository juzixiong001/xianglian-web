package com.xianglian.service.impl;

import com.xianglian.exception.BusinessException;
import com.xianglian.mapper.UserMapper;
import com.xianglian.pojo.User;
import com.xianglian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private final Map<String, String> smsCodeCache = new ConcurrentHashMap<>();
    private final Map<String, Long> smsCodeTimeCache = new ConcurrentHashMap<>();
    private static final String DEFAULT_PASSWORD = "123456";
    private static final long SMS_CODE_EXPIRE_MINUTES = 5;

    @Override
    public User getProfile(Long userId) {
        return userMapper.findById(userId);
    }

    @Override
    @Transactional
    public void updateProfile(User user) {
        userMapper.update(user);
    }

    @Override
    @Transactional
    public void register(User user) {
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new BusinessException(400, "用户名已存在");
        }
        if (user.getPhone() != null && userMapper.findByPhone(user.getPhone()) != null) {
            throw new BusinessException(400, "手机号已被注册");
        }
        userMapper.insert(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, password);
        if (user == null) {
            user = userMapper.findByPhoneAndPassword(username, password);
        }
        return user;
    }

    @Override
    public User loginByPhone(String phone, String password) {
        return userMapper.findByPhoneAndPassword(phone, password);
    }

    @Override
    @Transactional
    public User registerByPhone(String phone) {
        if (userMapper.findByPhone(phone) != null) {
            throw new BusinessException(400, "手机号已被注册");
        }
        User user = new User();
        user.setPhone(phone);
        user.setUsername(phone);
        user.setPassword(DEFAULT_PASSWORD);
        userMapper.insert(user);
        return userMapper.findByPhone(phone);
    }

    @Override
    public String sendSmsCode(String phone) {
        long now = System.currentTimeMillis();
        Long lastSendTime = smsCodeTimeCache.get(phone);
        if (lastSendTime != null && now - lastSendTime < 60000) {
            throw new BusinessException(400, "验证码发送过于频繁，请稍后再试");
        }
        
        String code = generateSmsCode();
        smsCodeCache.put(phone, code);
        smsCodeTimeCache.put(phone, now);
        
        System.out.println("【模拟短信发送】验证码：" + code + "，有效期5分钟");
        return code;
    }

    @Override
    public boolean verifySmsCode(String phone, String code) {
        String cachedCode = smsCodeCache.get(phone);
        Long sendTime = smsCodeTimeCache.get(phone);
        
        if (cachedCode == null || sendTime == null) {
            return false;
        }
        
        long now = System.currentTimeMillis();
        if (now - sendTime > SMS_CODE_EXPIRE_MINUTES * 60 * 1000) {
            smsCodeCache.remove(phone);
            smsCodeTimeCache.remove(phone);
            return false;
        }
        
        boolean isValid = cachedCode.equals(code);
        if (isValid) {
            smsCodeCache.remove(phone);
            smsCodeTimeCache.remove(phone);
        }
        return isValid;
    }

    private String generateSmsCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}