package com.xianglian.config;

import com.xianglian.utils.JwtUtils;
import com.xianglian.utils.TraceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 处理OPTIONS预检请求
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        
        // 跳过登录和注册接口的认证
        String path = request.getRequestURI();
        if (path.equals("/api/login") || path.equals("/api/register")
            || path.equals("/api/login/") || path.equals("/api/register/")
            || path.startsWith("/doc.html") || path.startsWith("/webjars")
            || path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")
            || path.equals("/swagger-ui.html") || path.equals("/favicon.ico")) {
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
            Long userId = jwtUtils.getUserIdFromToken(token);
            if (userId != null) {
                request.setAttribute("userId", userId);
                return true;
            }
        }
        
        // 没有token时返回401错误
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\": 401, \"message\": \"未授权，请登录\", \"data\": null, \"traceId\": \"" + TraceContext.getTraceId() + "\"}");
        return false;
    }
}