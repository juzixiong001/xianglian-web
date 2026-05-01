package com.xianglian.service.impl;

import com.xianglian.mapper.FavoriteMapper;
import com.xianglian.mapper.PostMapper;
import com.xianglian.pojo.Post;
import com.xianglian.service.RecommendService;
import com.xianglian.utils.TfIdfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> getRecommendations(Integer userId, Integer limit) {
        List<Post> userFavorites = favoriteMapper.findFavoritePostsByUserId(userId);
        
        if (userFavorites.isEmpty()) {
            return getHotPosts(limit);
        }
        
        List<Post> allPosts = postMapper.findAll();
        Set<Integer> favoritePostIds = new HashSet<>();
        for (Post post : userFavorites) {
            favoritePostIds.add(post.getId());
        }
        
        List<Post> candidatePosts = new ArrayList<>();
        for (Post post : allPosts) {
            if (!favoritePostIds.contains(post.getId()) && !post.getUserId().equals(userId)) {
                candidatePosts.add(post);
            }
        }
        
        if (candidatePosts.isEmpty()) {
            return getHotPosts(limit);
        }
        
        List<String> favoriteTexts = new ArrayList<>();
        for (Post post : userFavorites) {
            favoriteTexts.add(post.getTitle() + " " + post.getContent());
        }
        
        List<String> candidateTexts = new ArrayList<>();
        for (Post post : candidatePosts) {
            candidateTexts.add(post.getTitle() + " " + post.getContent());
        }
        
        List<String> allTexts = new ArrayList<>();
        allTexts.addAll(favoriteTexts);
        allTexts.addAll(candidateTexts);
        
        List<Map<String, Double>> allVectors = TfIdfUtil.calculateTfIdf(allTexts);
        
        List<Map<String, Double>> favoriteVectors = allVectors.subList(0, favoriteTexts.size());
        Map<String, Double> userPreference = TfIdfUtil.averageVectors(favoriteVectors);
        
        List<Map<String, Double>> candidateVectors = allVectors.subList(favoriteTexts.size(), allTexts.size());
        
        List<double[]> similarities = new ArrayList<>();
        for (int i = 0; i < candidatePosts.size(); i++) {
            double sim = TfIdfUtil.cosineSimilarity(userPreference, candidateVectors.get(i));
            similarities.add(new double[]{i, sim});
        }
        
        similarities.sort((a, b) -> Double.compare(b[1], a[1]));
        
        List<Post> recommendations = new ArrayList<>();
        int count = Math.min(limit, similarities.size());
        for (int i = 0; i < count; i++) {
            int index = (int) similarities.get(i)[0];
            recommendations.add(candidatePosts.get(index));
        }
        
        return recommendations;
    }

    @Override
    public List<Post> getHotPosts(Integer limit) {
        List<Post> allPosts = postMapper.findAll();
        allPosts.sort((a, b) -> b.getCreateTime().compareTo(a.getCreateTime()));
        return allPosts.subList(0, Math.min(limit, allPosts.size()));
    }
}