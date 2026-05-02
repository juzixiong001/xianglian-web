package com.xianglian.service.impl;

import com.xianglian.exception.BusinessException;
import com.xianglian.mapper.UserMapper;
import com.xianglian.pojo.User;
import com.xianglian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
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
        User existingUser = userMapper.findById(user.getId());
        if (existingUser == null) {
            throw new BusinessException(400, "用户不存在");
        }
        if (StringUtils.hasText(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userMapper.update(user);
    }

    @Override
    @Transactional
    public void register(User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            throw new BusinessException(400, "用户名不能为空");
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new BusinessException(400, "密码不能为空");
        }
        if (!StringUtils.hasText(user.getPhone())) {
            throw new BusinessException(400, "手机号不能为空");
        }
        
        if (user.getUsername().length() < 3 || user.getUsername().length() > 20) {
            throw new BusinessException(400, "用户名长度必须在3-20个字符之间");
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 30) {
            throw new BusinessException(400, "密码长度必须在6-30个字符之间");
        }
        if (!user.getPhone().matches("^1[3-9]\\d{9}$")) {
            throw new BusinessException(400, "手机号格式不正确");
        }
        
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new BusinessException(400, "用户名已存在");
        }
        if (userMapper.findByPhone(user.getPhone()) != null) {
            throw new BusinessException(400, "手机号已被注册");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    public User login(String username, String password) {
        if (!StringUtils.hasText(username)) {
            throw new BusinessException(400, "用户名不能为空");
        }
        if (!StringUtils.hasText(password)) {
            throw new BusinessException(400, "密码不能为空");
        }
        
        User user = userMapper.findByUsername(username);
        if (user == null) {
            user = userMapper.findByPhone(username);
        }
        
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User loginByPhone(String phone, String password) {
        User user = userMapper.findByPhone(phone);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    @Transactional
    public User registerByPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            throw new BusinessException(400, "手机号不能为空");
        }
        if (!phone.matches("^1[3-9]\\d{9}$")) {
            throw new BusinessException(400, "手机号格式不正确");
        }
        if (userMapper.findByPhone(phone) != null) {
            throw new BusinessException(400, "手机号已被注册");
        }
        
        User user = new User();
        user.setPhone(phone);
        user.setUsername(phone);
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        userMapper.insert(user);
        return userMapper.findByPhone(phone);
    }

    @Override
    public String sendSmsCode(String phone) {
        if (!StringUtils.hasText(phone)) {
            throw new BusinessException(400, "手机号不能为空");
        }
        if (!phone.matches("^1[3-9]\\d{9}$")) {
            throw new BusinessException(400, "手机号格式不正确");
        }
        
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
        if (!StringUtils.hasText(phone) || !StringUtils.hasText(code)) {
            return false;
        }
        
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