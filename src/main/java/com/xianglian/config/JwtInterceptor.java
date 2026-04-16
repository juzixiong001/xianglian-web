package com.xianglian.config;

import com.xianglian.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 跳过登录和注册接口的认证
        String path = request.getRequestURI();
        if (path.equals("/api/login") || path.equals("/api/register")) {
            return true;
        }
        
        // 对于其他接口，尝试获取token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Long userId = JwtUtils.getUserIdFromToken(token);
            if (userId != null) {
                request.setAttribute("userId", userId);
                return true;
            }
        }
        
        // 为了比赛展示，没有token时也放行，并设置默认userId为1
        request.setAttribute("userId", 25L);
        return true;
    }
}