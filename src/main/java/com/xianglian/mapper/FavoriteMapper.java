package com.xianglian.mapper;

import com.xianglian.pojo.Favorite;
import com.xianglian.pojo.Post;
import com.xianglian.pojo.Policy;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Insert("INSERT INTO favorite (user_id, post_id, target_type, target_id, create_time) VALUES (#{userId}, #{postId}, #{targetType}, #{targetId}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Favorite favorite);

    @Delete("DELETE FROM favorite WHERE id = #{id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM favorite WHERE id IN (${ids})")
    void deleteBatch(@Param("ids") String ids);

    @Select("SELECT * FROM favorite WHERE user_id = #{userId}")
    List<Favorite> findByUserId(Integer userId);

    @Select("SELECT * FROM favorite WHERE user_id = #{userId} AND target_type = #{targetType}")
    List<Favorite> findByUserIdAndTargetType(@Param("userId") Integer userId, @Param("targetType") String targetType);

    @Select("SELECT * FROM favorite WHERE user_id = #{userId} AND post_id = #{postId}")
    Favorite findByUserIdAndPostId(@Param("userId") Integer userId, @Param("postId") Integer postId);

    @Select("SELECT * FROM favorite WHERE user_id = #{userId} AND target_type = #{targetType} AND target_id = #{targetId}")
    Favorite findByUserIdAndTarget(@Param("userId") Integer userId, @Param("targetType") String targetType, @Param("targetId") Integer targetId);

    @Delete("DELETE FROM favorite WHERE user_id = #{userId} AND post_id = #{postId}")
    void deleteByUserIdAndPostId(@Param("userId") Integer userId, @Param("postId") Integer postId);

    @Delete("DELETE FROM favorite WHERE user_id = #{userId} AND target_type = #{targetType} AND target_id = #{targetId}")
    void deleteByUserIdAndTarget(@Param("userId") Integer userId, @Param("targetType") String targetType, @Param("targetId") Integer targetId);

    @Select("SELECT p.* FROM post p INNER JOIN favorite f ON p.id = f.target_id WHERE f.user_id = #{userId} AND f.target_type = 'post' ORDER BY f.create_time DESC")
    List<Post> findFavoritePostsByUserId(Integer userId);

    @Select("SELECT p.* FROM policy p INNER JOIN favorite f ON p.id = f.target_id WHERE f.user_id = #{userId} AND f.target_type = 'policy' ORDER BY f.create_time DESC")
    List<Policy> findFavoritePoliciesByUserId(Integer userId);

    @Select("SELECT COUNT(*) FROM favorite WHERE user_id = #{userId}")
    Integer countByUserId(Integer userId);

    @Select("SELECT COUNT(*) FROM favorite WHERE user_id = #{userId} AND target_type = #{targetType}")
    Integer countByUserIdAndType(@Param("userId") Integer userId, @Param("targetType") String targetType);
}