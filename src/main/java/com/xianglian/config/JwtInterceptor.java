package com.xianglian.config;

import com.xianglian.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 处理OPTIONS预检请求
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        
        // 跳过登录和注册接口的认证
        String path = request.getRequestURI();
        if (path.equals("/api/login") || path.equals("/api/register") || path.equals("/api/login/") || path.equals("/api/register/")) {
            return true;
        }
        
        // 对于其他接口，尝试获取token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
        }
        if (token == null || token.isEmpty()) {
            token = request.getParameter("token");
        }
        if (token != null && !token.isEmpty()) {
            Long userId = JwtUtils.getUserIdFromToken(token);
            if (userId != null) {
                request.setAttribute("userId", userId);
                return true;
            }
        }
        
        // 没有token时返回401错误
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"code\": 401, \"message\": \"未授权，请登录\", \"data\": null}");
        return false;
    }
}