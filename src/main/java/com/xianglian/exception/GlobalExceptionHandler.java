package com.xianglian.exception;

import com.xianglian.utils.Result;
import com.xianglian.utils.TraceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, Object>> handleSQLException(SQLException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 500);
        response.put("message", "数据库操作失败：" + e.getMessage());
        response.put("data", null);
        response.put("traceId", TraceContext.getTraceId());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 400);
        response.put("message", e.getMessage());
        response.put("data", null);
        response.put("traceId", TraceContext.getTraceId());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 500);
        response.put("message", "服务器内部错误：" + e.getMessage());
        response.put("data", null);
        response.put("traceId", TraceContext.getTraceId());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}