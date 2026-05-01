package com.xianglian.mapper;

import com.xianglian.pojo.Favorite;
import com.xianglian.pojo.Post;
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

    @Select("SELECT * FROM favorite WHERE user_id = #{userId} AND post_id = #{postId}")
    Favorite findByUserIdAndPostId(@Param("userId") Integer userId, @Param("postId") Integer postId);

    @Delete("DELETE FROM favorite WHERE user_id = #{userId} AND post_id = #{postId}")
    void deleteByUserIdAndPostId(@Param("userId") Integer userId, @Param("postId") Integer postId);

    @Select("SELECT p.* FROM post p INNER JOIN favorite f ON p.id = f.post_id WHERE f.user_id = #{userId} ORDER BY f.create_time DESC")
    List<Post> findFavoritePostsByUserId(Integer userId);

    @Select("SELECT COUNT(*) FROM favorite WHERE user_id = #{userId}")
    Integer countByUserId(Integer userId);
}