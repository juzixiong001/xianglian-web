package com.xianglian.mapper;

import com.xianglian.pojo.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT * FROM post")
    List<Post> findAll();

    @Insert("INSERT INTO post (user_id, title, content, type, create_time, update_time) VALUES (#{userId}, #{title}, #{content}, #{type}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Post post);

    @Delete("DELETE FROM post WHERE id = #{id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM post WHERE id IN (#{ids})")
    void deleteBatch(@Param("ids") String ids);

    @Select("SELECT * FROM post WHERE user_id = #{userId}")
    List<Post> findByUserId(Integer userId);
}