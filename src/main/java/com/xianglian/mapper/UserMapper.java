package com.xianglian.mapper;

import com.xianglian.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Long id);

    @Insert("INSERT INTO user (username, password, phone, create_time, update_time) VALUES (#{username}, #{password}, #{phone}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(String username, String password);

    @Update("UPDATE user SET phone = #{phone}, update_time = NOW() WHERE id = #{id}")
    void update(User user);
}