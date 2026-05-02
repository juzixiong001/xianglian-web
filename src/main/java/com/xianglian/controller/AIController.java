package com.xianglian.controller;

import com.xianglian.service.AIService;
import com.xianglian.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@Tag(name = "AI模块", description = "AI通用接口")
public class AIController {

    @Autowired
    private AIService aiService;

    @Operation(summary = "AI通用问答", description = "支持政策解读、供需分析、农业建议等，通过不同prompt实现")
    @PostMapping("/chat")
    public Result<Map<String, Object>> chat(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        if (prompt == null || prompt.trim().isEmpty()) {
            return Result.error("请输入prompt");
        }
        String answer = aiService.chat(prompt);
        Map<String, Object> data = new HashMap<>();
        data.put("answer", answer);
        return Result.success(data);
    }
}