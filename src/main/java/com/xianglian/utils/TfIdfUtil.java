package com.xianglian.utils;

import java.util.*;

public class TfIdfUtil {

    public static List<Map<String, Double>> calculateTfIdf(List<String> documents) {
        List<Map<String, Double>> tfIdfVectors = new ArrayList<>();
        
        Map<String, Integer> df = new HashMap<>();
        List<Map<String, Integer>> tfList = new ArrayList<>();
        
        for (String doc : documents) {
            Map<String, Integer> tf = new HashMap<>();
            String[] terms = tokenize(doc);
            int maxTf = 0;
            
            for (String term : terms) {
                tf.put(term, tf.getOrDefault(term, 0) + 1);
                maxTf = Math.max(maxTf, tf.get(term));
            }
            
            for (String term : tf.keySet()) {
                df.put(term, df.getOrDefault(term, 0) + 1);
            }
            
            tfList.add(tf);
        }
        
        int N = documents.size();
        
        for (int i = 0; i < N; i++) {
            Map<String, Double> tfIdf = new HashMap<>();
            Map<String, Integer> tf = tfList.get(i);
            
            for (Map.Entry<String, Integer> entry : tf.entrySet()) {
                String term = entry.getKey();
                int termFreq = entry.getValue();
                int docFreq = df.getOrDefault(term, 1);
                
                double tfValue = 0.5 + 0.5 * termFreq / Collections.max(tf.values());
                double idfValue = Math.log((double) (N + 1) / (docFreq + 0.5));
                tfIdf.put(term, tfValue * idfValue);
            }
            
            tfIdfVectors.add(tfIdf);
        }
        
        return tfIdfVectors;
    }

    public static double cosineSimilarity(Map<String, Double> vector1, Map<String, Double> vector2) {
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;
        
        Set<String> allTerms = new HashSet<>();
        allTerms.addAll(vector1.keySet());
        allTerms.addAll(vector2.keySet());
        
        for (String term : allTerms) {
            double v1 = vector1.getOrDefault(term, 0.0);
            double v2 = vector2.getOrDefault(term, 0.0);
            dotProduct += v1 * v2;
            norm1 += v1 * v1;
            norm2 += v2 * v2;
        }
        
        if (norm1 == 0 || norm2 == 0) {
            return 0.0;
        }
        
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    public static Map<String, Double> averageVectors(List<Map<String, Double>> vectors) {
        Map<String, Double> avgVector = new HashMap<>();
        
        if (vectors.isEmpty()) {
            return avgVector;
        }
        
        for (Map<String, Double> vector : vectors) {
            for (Map.Entry<String, Double> entry : vector.entrySet()) {
                avgVector.put(entry.getKey(), avgVector.getOrDefault(entry.getKey(), 0.0) + entry.getValue());
            }
        }
        
        int count = vectors.size();
        for (Map.Entry<String, Double> entry : avgVector.entrySet()) {
            avgVector.put(entry.getKey(), entry.getValue() / count);
        }
        
        return avgVector;
    }

    private static String[] tokenize(String text) {
        if (text == null || text.isEmpty()) {
            return new String[0];
        }
        
        text = text.toLowerCase().replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]", " ");
        return text.split("\\s+");
    }
}