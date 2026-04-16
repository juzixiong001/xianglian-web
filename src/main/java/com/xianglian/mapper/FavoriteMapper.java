package com.xianglian.mapper;

import com.xianglian.pojo.Favorite;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Insert("INSERT INTO favorite (user_id, post_id, create_time) VALUES (#{userId}, #{postId}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Favorite favorite);

    @Delete("DELETE FROM favorite WHERE id = #{id}")
    void deleteById(Integer id);


    @Delete("DELETE FROM favorite WHERE id IN (${ids})")
    void deleteBatch(@Param("ids") String ids);

    @Select("SELECT * FROM favorite WHERE user_id = #{userId}")
    List<Favorite> findByUserId(Integer userId);
}