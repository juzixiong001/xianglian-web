package com.xianglian.controller;

import com.xianglian.service.AIService;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@Tag(name = "AI模块", description = "AI智能分析接口")
public class AIController {

    @Autowired
    private AIService aiService;

    @Operation(summary = "AI智能问答")
    @PostMapping("/chat")
    public Result chat(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        if (question == null || question.trim().isEmpty()) {
            return Result.error("请输入问题");
        }
        String answer = aiService.chat(question);
        Map<String, Object> data = new HashMap<>();
        data.put("answer", answer);
        return Result.success(data);
    }

    @Operation(summary = "政策智能匹配")
    @PostMapping("/policy-match")
    public Result matchPolicy(@RequestBody Map<String, Object> userProfile) {
        String result = aiService.matchPolicy(userProfile);
        Map<String, Object> data = new HashMap<>();
        data.put("result", result);
        return Result.success(data);
    }

    @Operation(summary = "供需分析")
    @PostMapping("/supply-demand")
    public Result analyzeSupplyDemand(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        if (query == null || query.trim().isEmpty()) {
            return Result.error("请输入查询内容");
        }
        String result = aiService.analyzeSupplyDemand(query);
        Map<String, Object> data = new HashMap<>();
        data.put("result", result);
        return Result.success(data);
    }

    @Operation(summary = "农业优化建议")
    @PostMapping("/agriculture-optimize")
    public Result optimizeAgriculture(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        if (question == null || question.trim().isEmpty()) {
            return Result.error("请输入问题");
        }
        String advice = aiService.optimizeAgriculture(question);
        Map<String, Object> data = new HashMap<>();
        data.put("advice", advice);
        return Result.success(data);
    }

    @Operation(summary = "内容概括")
    @PostMapping("/summarize")
    public Result summarizeContent(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        if (content == null || content.trim().isEmpty()) {
            return Result.error("请输入需要概括的内容");
        }
        String summary = aiService.summarizeContent(content);
        Map<String, Object> data = new HashMap<>();
        data.put("summary", summary);
        return Result.success(data);
    }

    @Operation(summary = "AI服务健康检查")
    @GetMapping("/health")
    public Result healthCheck() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "ok");
        data.put("service", "ai-service");
        return Result.success(data);
    }
}