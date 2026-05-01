package com.xianglian.service;

import java.util.Map;

public interface AIService {
    String chat(String question);
    String matchPolicy(Map<String, Object> userProfile);
    String analyzeSupplyDemand(String query);
    String optimizeAgriculture(String question);
    String summarizeContent(String content);
}