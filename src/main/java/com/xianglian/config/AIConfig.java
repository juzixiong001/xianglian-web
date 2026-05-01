package com.xianglian.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ai")
public class AIConfig {
    private Provider provider = new Provider();
    private Cache cache = new Cache();
    private RateLimit rateLimit = new RateLimit();

    public static class Provider {
        private String type = "deepseek";
        private Doubao doubao = new Doubao();
        private Tongyi tongyi = new Tongyi();
        private DeepSeek deepSeek = new DeepSeek();

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public Doubao getDoubao() { return doubao; }
        public void setDoubao(Doubao doubao) { this.doubao = doubao; }
        public Tongyi getTongyi() { return tongyi; }
        public void setTongyi(Tongyi tongyi) { this.tongyi = tongyi; }
        public DeepSeek getDeepSeek() { return deepSeek; }
        public void setDeepSeek(DeepSeek deepSeek) { this.deepSeek = deepSeek; }
    }

    public static class Doubao {
        private String apiKey;
        private String secretKey;
        private String apiUrl = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions";
        private int maxTokens = 1500;
        private double temperature = 0.7;

        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        public String getSecretKey() { return secretKey; }
        public void setSecretKey(String secretKey) { this.secretKey = secretKey; }
        public String getApiUrl() { return apiUrl; }
        public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
        public int getMaxTokens() { return maxTokens; }
        public void setMaxTokens(int maxTokens) { this.maxTokens = maxTokens; }
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
    }

    public static class Tongyi {
        private String apiKey;
        private String apiUrl = "https://dashscope.aliyuncs.com/api/text/chat";
        private int maxTokens = 1500;
        private double temperature = 0.7;

        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        public String getApiUrl() { return apiUrl; }
        public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
        public int getMaxTokens() { return maxTokens; }
        public void setMaxTokens(int maxTokens) { this.maxTokens = maxTokens; }
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
    }

    public static class DeepSeek {
        private String apiKey;
        private String apiUrl = "https://api.deepseek.com/v1/chat/completions";
        private int maxTokens = 1500;
        private double temperature = 0.7;
        private String model = "deepseek-chat";

        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        public String getApiUrl() { return apiUrl; }
        public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
        public int getMaxTokens() { return maxTokens; }
        public void setMaxTokens(int maxTokens) { this.maxTokens = maxTokens; }
        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
    }

    public static class Cache {
        private boolean enabled = true;
        private int expireMinutes = 30;

        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
        public int getExpireMinutes() { return expireMinutes; }
        public void setExpireMinutes(int expireMinutes) { this.expireMinutes = expireMinutes; }
    }

    public static class RateLimit {
        private int maxRequestsPerMinute = 30;
        private int maxRequestsPerDay = 1000;

        public int getMaxRequestsPerMinute() { return maxRequestsPerMinute; }
        public void setMaxRequestsPerMinute(int maxRequestsPerMinute) { this.maxRequestsPerMinute = maxRequestsPerMinute; }
        public int getMaxRequestsPerDay() { return maxRequestsPerDay; }
        public void setMaxRequestsPerDay(int maxRequestsPerDay) { this.maxRequestsPerDay = maxRequestsPerDay; }
    }

    public Provider getProvider() { return provider; }
    public void setProvider(Provider provider) { this.provider = provider; }
    public Cache getCache() { return cache; }
    public void setCache(Cache cache) { this.cache = cache; }
    public RateLimit getRateLimit() { return rateLimit; }
    public void setRateLimit(RateLimit rateLimit) { this.rateLimit = rateLimit; }
}