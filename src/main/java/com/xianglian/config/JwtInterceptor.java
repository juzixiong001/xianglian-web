package com.xianglian.config;

import com.xianglian.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (JwtUtils.validateToken(token)) {
                Integer userId = JwtUtils.getUserIdFromToken(token);
                request.setAttribute("userId", userId);
                return true;
            }
        }
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write("{\"code\": 401, \"message\": \"???\"}");
        return false;
    }
}
