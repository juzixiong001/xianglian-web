package com.xianglian.service.impl;

import com.xianglian.mapper.FavoriteMapper;
import com.xianglian.pojo.Favorite;
import com.xianglian.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    @Transactional
    public void addFavorite(Favorite favorite) {
        favoriteMapper.insert(favorite);
    }

    @Override
    @Transactional
    public void removeFavorite(Integer id) {
        favoriteMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void removeBatchFavorites(String ids) {
        favoriteMapper.deleteBatch(ids);
    }

    @Override
    public List<Favorite> getMyFavorites(Integer userId) {
        return favoriteMapper.findByUserId(userId);
    }
}