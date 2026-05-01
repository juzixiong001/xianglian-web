package com.xianglian.service.impl;

import com.xianglian.config.AIConfig;
import com.xianglian.service.AIService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AIServiceImpl implements AIService {

    @Autowired
    private AIConfig aiConfig;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final Map<String, String> responseCache = new ConcurrentHashMap<>();
    private final Map<String, AtomicInteger> dailyRequestCounts = new ConcurrentHashMap<>();
    private final Map<String, Long> lastRequestTimes = new ConcurrentHashMap<>();
    private volatile String cachedAccessToken = null;
    private volatile long accessTokenExpireTime = 0;

    public AIServiceImpl() {
        this.webClient = WebClient.create();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String chat(String question) {
        return callAI(question, "你是一个智能助手，请用简洁的语言回答问题，控制在100字以内。");
    }

    @Override
    public String matchPolicy(Map<String, Object> userProfile) {
        String profile = formatUserProfile(userProfile);
        String prompt = "用户信息：" + profile + "\n请根据用户情况推荐1-2条相关农业政策，用简洁语言说明匹配理由，控制在150字以内。";
        return callAI(prompt, "你是农业政策匹配专家，擅长根据用户情况推荐合适的农业政策。");
    }

    @Override
    public String analyzeSupplyDemand(String query) {
        String prompt = "供需查询：" + query + "\n请分析供需关系，提供匹配建议，控制在100字以内。";
        return callAI(prompt, "你是农产品供需分析专家，擅长分析市场供需关系。");
    }

    @Override
    public String optimizeAgriculture(String question) {
        String prompt = "农业问题：" + question + "\n请提供具体的生产优化建议，控制在150字以内。";
        return callAI(prompt, "你是农业生产专家，擅长提供农业生产技能优化建议。");
    }

    @Override
    public String summarizeContent(String content) {
        String prompt = "请概括以下内容的核心要点，控制在50字以内：\n" + content;
        return callAI(prompt, "你是内容摘要专家，擅长提炼核心信息。");
    }

    private String callAI(String question, String systemPrompt) {
        String cacheKey = generateCacheKey(question);
        
        if (aiConfig.getCache().isEnabled()) {
            String cached = responseCache.get(cacheKey);
            if (cached != null) {
                return cached;
            }
        }

        if (!checkRateLimit()) {
            return "当前AI服务调用过于频繁，请稍后再试。";
        }

        String response;
        try {
            response = switch (aiConfig.getProvider().getType()) {
                case "tongyi" -> callTongyiAPI(question, systemPrompt);
                case "deepseek" -> callDeepSeekAPI(question, systemPrompt);
                default -> callDoubaoAPI(question, systemPrompt);
            };
        } catch (Exception e) {
            return "AI服务暂时不可用，请稍后重试。";
        }

        if (aiConfig.getCache().isEnabled() && response != null && !response.isEmpty()) {
            responseCache.put(cacheKey, response);
            scheduleCacheCleanup(cacheKey);
        }

        return response;
    }

    private String callDoubaoAPI(String question, String systemPrompt) throws Exception {
        String accessToken = getDoubaoAccessToken();
        
        Map<String, Object> body = new HashMap<>();
        body.put("messages", Arrays.asList(
            Map.of("role", "system", "content", systemPrompt),
            Map.of("role", "user", "content", question)
        ));
        body.put("max_tokens", aiConfig.getProvider().getDoubao().getMaxTokens());
        body.put("temperature", aiConfig.getProvider().getDoubao().getTemperature());

        String response = webClient.post()
            .uri(aiConfig.getProvider().getDoubao().getApiUrl() + "?access_token=" + accessToken)
            .header("Content-Type", "application/json")
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return parseDoubaoResponse(response);
    }

    private String callTongyiAPI(String question, String systemPrompt) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("model", "qwen-turbo");
        body.put("input", Map.of(
            "messages", Arrays.asList(
                Map.of("role", "system", "content", systemPrompt),
                Map.of("role", "user", "content", question)
            )
        ));
        body.put("parameters", Map.of(
            "max_tokens", aiConfig.getProvider().getTongyi().getMaxTokens(),
            "temperature", aiConfig.getProvider().getTongyi().getTemperature()
        ));

        String response = webClient.post()
            .uri(aiConfig.getProvider().getTongyi().getApiUrl())
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + aiConfig.getProvider().getTongyi().getApiKey())
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return parseTongyiResponse(response);
    }

    private String callDeepSeekAPI(String question, String systemPrompt) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("model", aiConfig.getProvider().getDeepSeek().getModel());
        body.put("messages", Arrays.asList(
            Map.of("role", "system", "content", systemPrompt),
            Map.of("role", "user", "content", question)
        ));
        body.put("max_tokens", aiConfig.getProvider().getDeepSeek().getMaxTokens());
        body.put("temperature", aiConfig.getProvider().getDeepSeek().getTemperature());

        String response = webClient.post()
            .uri(aiConfig.getProvider().getDeepSeek().getApiUrl())
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + aiConfig.getProvider().getDeepSeek().getApiKey())
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return parseDeepSeekResponse(response);
    }

    private synchronized String getDoubaoAccessToken() throws Exception {
        long now = System.currentTimeMillis();
        if (cachedAccessToken != null && now < accessTokenExpireTime) {
            return cachedAccessToken;
        }

        String authUrl = "https://aip.baidubce.com/oauth/2.0/token" +
            "?grant_type=client_credentials" +
            "&client_id=" + aiConfig.getProvider().getDoubao().getApiKey() +
            "&client_secret=" + aiConfig.getProvider().getDoubao().getSecretKey();

        String response = webClient.post()
            .uri(authUrl)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        JsonNode json = objectMapper.readTree(response);
        cachedAccessToken = json.get("access_token").asText();
        int expiresIn = json.get("expires_in").asInt();
        accessTokenExpireTime = now + (expiresIn - 60) * 1000L;

        return cachedAccessToken;
    }

    private String parseDoubaoResponse(String response) throws Exception {
        JsonNode json = objectMapper.readTree(response);
        if (json.has("result")) {
            return json.get("result").asText().trim();
        }
        if (json.has("error_msg")) {
            return "AI服务错误：" + json.get("error_msg").asText();
        }
        return response;
    }

    private String parseTongyiResponse(String response) throws Exception {
        JsonNode json = objectMapper.readTree(response);
        if (json.has("output")) {
            return json.get("output").get("text").asText().trim();
        }
        if (json.has("code")) {
            return "AI服务错误：" + json.get("message").asText();
        }
        return response;
    }

    private String parseDeepSeekResponse(String response) throws Exception {
        JsonNode json = objectMapper.readTree(response);
        if (json.has("choices") && json.get("choices").isArray() && json.get("choices").size() > 0) {
            JsonNode choice = json.get("choices").get(0);
            if (choice.has("message") && choice.get("message").has("content")) {
                return choice.get("message").get("content").asText().trim();
            }
        }
        if (json.has("error")) {
            String errorMessage = json.get("error").has("message") ? json.get("error").get("message").asText() : "未知错误";
            return "AI服务错误：" + errorMessage;
        }
        return response;
    }

    private boolean checkRateLimit() {
        String today = LocalDate.now().toString();
        AtomicInteger dailyCount = dailyRequestCounts.computeIfAbsent(today, k -> new AtomicInteger(0));
        
        int currentDaily = dailyCount.incrementAndGet();
        if (currentDaily > aiConfig.getRateLimit().getMaxRequestsPerDay()) {
            dailyCount.decrementAndGet();
            return false;
        }

        String clientKey = "global";
        long now = System.currentTimeMillis();
        Long lastTime = lastRequestTimes.get(clientKey);
        
        if (lastTime != null && now - lastTime < 60000L / aiConfig.getRateLimit().getMaxRequestsPerMinute()) {
            return false;
        }
        
        lastRequestTimes.put(clientKey, now);
        return true;
    }

    private String generateCacheKey(String question) {
        return "ai_cache:" + question.hashCode();
    }

    private void scheduleCacheCleanup(String cacheKey) {
        new Thread(() -> {
            try {
                Thread.sleep(aiConfig.getCache().getExpireMinutes() * 60 * 1000L);
                responseCache.remove(cacheKey);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private String formatUserProfile(Map<String, Object> profile) {
        StringBuilder sb = new StringBuilder();
        if (profile.containsKey("area")) sb.append("地区：").append(profile.get("area")).append("；");
        if (profile.containsKey("cropType")) sb.append("作物类型：").append(profile.get("cropType")).append("；");
        if (profile.containsKey("scale")) sb.append("种植规模：").append(profile.get("scale")).append("；");
        if (profile.containsKey("needs")) sb.append("需求：").append(profile.get("needs")).append("；");
        return sb.toString();
    }
}